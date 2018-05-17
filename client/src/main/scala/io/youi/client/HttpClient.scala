package io.youi.client

import java.io.{File, IOException}
import java.util.concurrent.TimeUnit

import io.circe.{Decoder, Encoder, Json, Printer}
import io.circe.parser._
import io.circe.syntax._
import io.youi.http._
import io.youi.net.{ContentType, URL}
import org.powerscala.io._

import scala.collection.JavaConverters._
import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * Asynchronous HttpClient for simple request response support.
  *
  * Adds support for simple restful request/response JSON support.
  *
  * @param saveDirectory the directory to save response content of a non-textual type
  */
class HttpClient(saveDirectory: File = new File(System.getProperty("java.io.tmpdir")),
                 http2: Boolean = false,
                 dropNullValues: Boolean = false,
                 timeout: FiniteDuration = 15.seconds,
                 defaultRetry: Int = 0,
                 defaultRetryDelay: FiniteDuration = 5.seconds,
                 pingInterval: Option[FiniteDuration] = None) {
  private lazy val printer = Printer.spaces2.copy(dropNullValues = dropNullValues)
  private lazy val client = {
    val b = new okhttp3.OkHttpClient.Builder()
    b.connectTimeout(timeout.toMillis, TimeUnit.MILLISECONDS)
    b.readTimeout(timeout.toMillis, TimeUnit.MILLISECONDS)
    b.writeTimeout(timeout.toMillis, TimeUnit.MILLISECONDS)
    pingInterval.foreach(d => b.pingInterval(d.toMillis, TimeUnit.MILLISECONDS))
    b.build()
  }

  /**
    * Sends an HttpRequest and receives an asynchronous HttpResponse future.
    *
    * @param request the request to send
    * @param retry the number of times to retry a failed request. This defaults to zero as most requests are not
    *              idempotent and calling multiple times can cause side-effects
    * @param retryDelay if a failure occurs and a retry must occur, how long to wait until retrying. This defaults to
    *                   5.0 seconds
    * @return Future[HttpResponse]
    */
  def send(request: HttpRequest,
           retry: Int = defaultRetry,
           retryDelay: FiniteDuration = defaultRetryDelay): Future[HttpResponse] = {
    val req = requestToOk(request)
    val promise = Promise[HttpResponse]
    client.newCall(req).enqueue(new okhttp3.Callback {
      override def onResponse(call: okhttp3.Call, res: okhttp3.Response): Unit = {
        val response = responseFromOk(res)
        promise.success(response)
      }

      override def onFailure(call: okhttp3.Call, exc: IOException): Unit = promise.failure(exc)
    })
    promise.future.recoverWith {
      case t: Throwable if retry > 0 => {
        scribe.warn(s"Request to ${request.url} failed (${t.getMessage}). Retrying after $retryDelay seconds...")
        Future(Thread.sleep(retryDelay.toMillis)).flatMap(_ => send(request, retry - 1, retryDelay))
      }
    }
  }

  private def requestToOk(request: HttpRequest): okhttp3.Request = {
    val r = new okhttp3.Request.Builder().url(request.url.toString)

    // Headers
    request.headers.map.foreach {
      case (key, values) => values.foreach(r.addHeader(key, _))
    }

    def ct(contentType: ContentType): okhttp3.MediaType = okhttp3.MediaType.parse(contentType.outputString)

    // Content
    val body = request.content.map {
      case StringContent(value, contentType, _) => okhttp3.RequestBody.create(ct(contentType), value)
      case FileContent(file, contentType, _) => okhttp3.RequestBody.create(ct(contentType), file)
      case FormDataContent(data) => {
        val form = new okhttp3.MultipartBody.Builder()
        form.setType(ct(ContentType.`multipart/form-data`))
        data.foreach {
          case FormData(key, entries) => entries.foreach {
            case StringEntry(value, _) => form.addFormDataPart(key, value)
            case FileEntry(fileName, file, headers) => {
              val partType = Headers.`Content-Type`.value(headers).getOrElse(ContentType.`application/octet-stream`)
              val part = okhttp3.RequestBody.create(ct(partType), file)
              form.addFormDataPart(key, fileName, part)
            }
          }
        }
        form.build()
      }
      case c => throw new RuntimeException(s"Unsupported request content: $c")
    }.orNull

    // Method
    r.method(request.method.value, body)
    r.build()
  }

  private def responseFromOk(r: okhttp3.Response): HttpResponse = {
    // Status
    val status = HttpStatus(code = r.code(), message = r.message())

    // Headers
    val headersMap = r.headers().names().asScala.toList.map { key =>
      key -> r.headers(key).asScala.toList
    }.toMap
    val headers = Headers(headersMap)

    // Content
    val contentType = Headers.`Content-Type`.value(headers).getOrElse(ContentType.`application/octet-stream`)
    val contentLength = Headers.`Content-Length`.value(headers)
    val content = Option(r.body()).map { responseBody =>
      if (contentToString(contentType, contentLength)) {
        Content.string(responseBody.string(), contentType)
      } else {
        val file = File.createTempFile("youi", "client", saveDirectory)
        IO.stream(responseBody.byteStream(), file)
        Content.file(file, contentType)
      }
    }

    HttpResponse(
      status = status,
      headers = headers,
      content = content
    )
  }

  protected def contentToString(contentType: ContentType, contentLength: Option[Long]): Boolean = {
    contentType.`type` == "text" || contentType.subType == "json"
  }

  /**
    * Default error handler for restful bad response statuses.
    *
    * @tparam Response the type of response
    * @return throws a RuntimeException when an error occurs
    */
  protected def defaultErrorHandler[Response]: ErrorHandler[Response] = new ErrorHandler[Response] {
    override def apply(request: HttpRequest, response: HttpResponse, throwable: Option[Throwable]) = throwable match {
      case Some(t) => throw new RuntimeException(s"Error from server: ${response.status.message} (${response.status.code}) for ${request.url}.", t)
      case None => throw new RuntimeException(s"Error from server: ${response.status.message} (${response.status.code}) for ${request.url}.")
    }
  }

  /**
    * Builds on the send method by supporting basic restful calls that take a case class as the request and returns a
    * case class as the response.
    *
    * @param url the URL of the endpoint
    * @param request the request instance
    * @param headers the headers if any to provide
    * @param errorHandler error handling support if the response status is not Success
    * @param encoder circe encoding of the Request
    * @param decoder circe decoding of the Response
    * @tparam Request the request type
    * @tparam Response the response type
    * @return Future[Response]
    */
  def restful[Request, Response](url: URL,
                                 request: Request,
                                 headers: Headers = Headers.empty,
                                 errorHandler: ErrorHandler[Response] = defaultErrorHandler[Response],
                                 method: Method = Method.Post,
                                 processor: Json => Json = (json: Json) => json,
                                 retry: Int = defaultRetry,
                                 retryDelay: FiniteDuration = defaultRetryDelay)
                                (implicit encoder: Encoder[Request], decoder: Decoder[Response]): Future[Response] = {
    val requestJson = printer.pretty(processor(request.asJson))
    val httpRequest = HttpRequest(
      method = method,
      url = url,
      headers = headers,
      content = Some(StringContent(requestJson, ContentType.`application/json`))
    )
    send(httpRequest, retry, retryDelay).map { response =>
      val responseJson = response.content.map {
        case content: StringContent => content.value
        case content: FileContent => IO.stream(content.file, new StringBuilder).toString
        case content => throw new RuntimeException(s"$content not supported")
      }.getOrElse("")
      if (responseJson.isEmpty) throw new RuntimeException(s"No content received in response for $url.")
      decode[Response](responseJson) match {
        case Left(error) => errorHandler(httpRequest, response, Some(error))
        case Right(result) => result
      }
    }
  }

  /**
    * Builds on the send method by supporting basic restful calls that calls a URL and returns a case class as the
    * response.
    *
    * @param url the URL of the endpoint
    * @param headers the headers if any to provide
    * @param errorHandler error handling support if the response status is not Success
    * @param decoder circe decoding of the Response
    * @tparam Response the response type
    * @return Future[Response]
    */
  def call[Response](url: URL,
                     method: Method = Method.Get,
                     headers: Headers = Headers.empty,
                     errorHandler: ErrorHandler[Response] = defaultErrorHandler[Response],
                     retry: Int = defaultRetry,
                     retryDelay: FiniteDuration = defaultRetryDelay)
                    (implicit decoder: Decoder[Response]): Future[Response] = {
    val request = HttpRequest(
      method = method,
      url = url,
      headers = headers
    )
    send(request, retry, retryDelay).map { response =>
      val responseJson = response.content.map {
        case content: StringContent => content.value
        case content: FileContent => IO.stream(content.file, new StringBuilder).toString
        case content => throw new RuntimeException(s"$content not supported")
      }.getOrElse("")
      if (response.status.isSuccess) {
        if (responseJson.isEmpty) throw new RuntimeException(s"No content received in response for $url.")
        decode[Response](responseJson) match {
          case Left(error) => errorHandler(request, response, Some(error))
          case Right(result) => result
        }
      } else {
        errorHandler(request, response, None)
      }
    }
  }
}