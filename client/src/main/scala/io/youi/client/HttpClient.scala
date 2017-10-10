package io.youi.client

import java.io.File
import java.net.URI
import java.nio.charset.StandardCharsets

import io.circe.{Decoder, Encoder, Json, Printer}
import io.circe.parser._
import io.circe.syntax._
import io.youi.http.{Content, FileContent, FileEntry, FormData, FormDataContent, Headers, HttpRequest, HttpResponse, Method, Status, StringContent, StringEntry}
import io.youi.net.{ContentType, URL}
import org.apache.http.{HttpEntity, HttpResponse => ApacheHttpResponse}
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.concurrent.FutureCallback
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.nio.client.{CloseableHttpAsyncClient, HttpAsyncClients}
import org.apache.http.nio.entity.{NFileEntity, NStringEntity}
import org.powerscala.io._

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Asynchronous HttpClient for simple request response support.
  *
  * Adds support for simple restful request/response JSON support.
  *
  * @param saveDirectory the directory to save response content of a non-textual type
  */
class HttpClient(saveDirectory: File = new File(System.getProperty("java.io.tmpdir")), dropNullKeys: Boolean = false) {
  private lazy val printer = Printer.spaces2.copy(dropNullKeys = dropNullKeys)
  private val asyncClient: CloseableHttpAsyncClient = {
    val client = HttpAsyncClients.createDefault()
    client.start()
    client
  }

  /**
    * Sends an HttpRequest and receives an asynchronous HttpResponse future.
    *
    * @param request the request to send
    * @return Future[HttpResponse]
    */
  def send(request: HttpRequest): Future[HttpResponse] = {
    val req: HttpEntityEnclosingRequestBase = new HttpEntityEnclosingRequestBase {
      setURI(new URI(request.url.toString))

      override def getMethod: String = request.method.value
    }
    request.headers.map.foreach {
      case (key, values) => values.foreach { value =>
        req.addHeader(key, value)
      }
    }
    def ct(contentType: ContentType) = org.apache.http.entity.ContentType.getByMimeType(contentType.mimeType)
    val content: Option[HttpEntity] = request.content.map {
      case c: StringContent => new NStringEntity(c.value, ct(c.contentType))
      case c: FileContent => new NFileEntity(c.file, ct(c.contentType))
      case c: FormDataContent => {
        val b = MultipartEntityBuilder.create()
        import org.apache.http.entity
        c.data.foreach {
          case FormData(key, entries) => entries.foreach {
            case StringEntry(value, headers) => {
              val contentType = entity.ContentType.create(Headers.`Content-Type`.value(headers).getOrElse(ContentType.`text/plain`).outputString, StandardCharsets.UTF_8)
              b.addTextBody(key, value, contentType)
            }
            case FileEntry(fileName, file, headers) => {
              val contentType = entity.ContentType.create(Headers.`Content-Type`.value(headers).getOrElse(ContentType.`application/octet-stream`).outputString, StandardCharsets.UTF_8)
              b.addBinaryBody(key, file, contentType, fileName)
            }
          }
        }
        b.setCharset(StandardCharsets.UTF_8)
        b.setContentType(entity.ContentType.create(ContentType.`multipart/form-data`.outputString, StandardCharsets.UTF_8))
        val multipart = b.build()
        new MultipartEntityWrapper(multipart)
      }
      case c => throw new RuntimeException(s"Unsupported request content: $c")
    }
    content.foreach(req.setEntity)
    val promise = Promise[HttpResponse]
    asyncClient.execute(req, new FutureCallback[ApacheHttpResponse] {
      override def cancelled(): Unit = promise.failure(new RuntimeException("Cancelled!"))

      override def failed(exc: Exception): Unit = promise.failure(exc)

      override def completed(result: ApacheHttpResponse): Unit = {
        var headers = Headers.empty
        result.getAllHeaders.foreach { header =>
          headers = headers.withHeader(header.getName, header.getValue)
        }
        val contentType = Headers.`Content-Type`.value(headers).getOrElse(ContentType.`application/octet-stream`)
        val contentLength = Headers.`Content-Length`.value(headers)
        val content: Option[Content] = if (contentLength.contains(0L)) {
          None
        } else if (contentType.`type` == "text" || contentType.subType == "json") {
          val body = IO.stream(result.getEntity.getContent, new StringBuilder).toString
          Some(Content.string(body, contentType))
        } else {
          val file = File.createTempFile("youi", "client", saveDirectory)
          IO.stream(result.getEntity.getContent, file)
          Some(Content.file(file, contentType))
        }

        promise.success(HttpResponse(
          Status(result.getStatusLine.getStatusCode, result.getStatusLine.getReasonPhrase),
          headers,
          content
        ))
      }
    })

    promise.future
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
                                 processor: Json => Json = (json: Json) => json)
                                (implicit encoder: Encoder[Request], decoder: Decoder[Response]): Future[Response] = {
    val requestJson = printer.pretty(processor(request.asJson))
    val httpRequest = HttpRequest(
      method = method,
      url = url,
      headers = headers,
      content = Some(StringContent(requestJson, ContentType.`application/json`))
    )
    send(httpRequest).map { response =>
      val responseJson = response.content.map {
        case content: StringContent => content.value
        case content: FileContent => IO.stream(content.file, new StringBuilder).toString
        case content => throw new RuntimeException(s"$content not supported")
      }.getOrElse("")
      if (response.status.isSuccess) {
        if (responseJson.isEmpty) throw new RuntimeException(s"No content received in response for $url.")
        decode[Response](responseJson) match {
          case Left(error) => errorHandler(httpRequest, response, Some(error))
          case Right(result) => result
        }
      } else {
        errorHandler(httpRequest, response, None)
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
                     errorHandler: ErrorHandler[Response] = defaultErrorHandler[Response])
                    (implicit decoder: Decoder[Response]): Future[Response] = {
    val request = HttpRequest(
      method = method,
      url = url,
      headers = headers
    )
    send(request).map { response =>
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

  /**
    * Disposes and cleans up this client instance.
    */
  def dispose(): Unit = asyncClient.close()
}

trait ErrorHandler[Response] {
  def apply(request: HttpRequest, response: HttpResponse, throwable: Option[Throwable]): Response
}