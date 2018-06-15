package io.youi.client

import java.io.{File, IOException}
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import io.youi.http._
import io.youi.net.ContentType
import org.powerscala.io._

import scala.collection.JavaConverters._
import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Success, Failure}

/**
  * Asynchronous HttpClient for simple request response support.
  *
  * Adds support for simple restful request/response JSON support.
  *
  * @param saveDirectory the directory to save response content of a non-textual type
  */
case class JVMHttpClient(saveDirectory: File = new File(System.getProperty("java.io.tmpdir")),
                         http2: Boolean = false,
                         dropNullValues: Boolean = false,
                         timeout: FiniteDuration = 15.seconds,
                         defaultRetry: Int = 0,
                         defaultRetryDelay: FiniteDuration = 5.seconds,
                         pingInterval: Option[FiniteDuration] = None,
                         connectionPool: ConnectionPool = ConnectionPool.default) extends HttpClient {
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
  override def send(request: HttpRequest,
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
    val future = promise.future.recoverWith {
      case t: Throwable if retry > 0 => {
        scribe.warn(s"Request to ${request.url} failed (${t.getMessage}). Retrying after $retryDelay seconds...")
        Future(Thread.sleep(retryDelay.toMillis)).flatMap(_ => send(request, retry - 1, retryDelay))
      }
    }
    JVMHttpClient.process(future)
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


  override protected def content2String(content: Content): String = content match {
    case c: StringContent => c.value
    case c: FileContent => IO.stream(c.file, new StringBuilder).toString
    case _ => throw new RuntimeException(s"$content not supported")
  }

  def logStats(): Unit = {
    scribe.info(s"HttpClient stats - Pool[active: ${connectionPool.active}, idle: ${connectionPool.idle}, total: ${connectionPool.total}], Global[active: ${JVMHttpClient.active}, successful: ${JVMHttpClient.successful}, failure: ${JVMHttpClient.failure}, total: ${JVMHttpClient.total}]")
  }
}

object JVMHttpClient {
  private[client] val _total = new AtomicLong(0L)
  private[client] val _active = new AtomicLong(0L)
  private[client] val _successful = new AtomicLong(0L)
  private[client] val _failure = new AtomicLong(0L)

  private[client] def process(future: Future[HttpResponse]): Future[HttpResponse] = {
    _total.incrementAndGet()
    _active.incrementAndGet()
    future.onComplete {
      case Success(_) => {
        _successful.incrementAndGet()
        _active.decrementAndGet()
      }
      case Failure(_) => {
        _failure.incrementAndGet()
        _active.decrementAndGet()
      }
    }
    future
  }

  def total: Long = _total.get()
  def active: Long = _active.get()
  def successful: Long = _successful.get()
  def failure: Long = _failure.get()
}