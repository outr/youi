package io.youi.client

import java.io.{File, IOException}
import java.net.InetAddress
import java.util
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import io.youi.http._
import io.youi.http.content._
import io.youi.net.ContentType
import okhttp3.Dns
import org.powerscala.io._

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}

/**
  * Asynchronous HttpClient for simple request response support.
  *
  * Adds support for simple restful request/response JSON support.
  */
class JVMHttpClientImplementation(config: HttpClientConfig) extends HttpClientImplementation(config) {
  private lazy val client = {
    val b = new okhttp3.OkHttpClient.Builder()
    b.connectTimeout(config.timeout.toMillis, TimeUnit.MILLISECONDS)
    b.readTimeout(config.timeout.toMillis, TimeUnit.MILLISECONDS)
    b.writeTimeout(config.timeout.toMillis, TimeUnit.MILLISECONDS)
    b.dns(new Dns {
      override def lookup(hostname: String): util.List[InetAddress] = {
        val list = new util.ArrayList[InetAddress]()
        config.dns.lookup(hostname) match {
          case Some(ip) => list.add(InetAddress.getByAddress(ip.address.map(_.toByte)))
          case None => // None
        }
        list
      }
    })
    config.pingInterval.foreach(d => b.pingInterval(d.toMillis, TimeUnit.MILLISECONDS))
    b.build()
  }

  override def send(request: HttpRequest,
                    executionContext: ExecutionContext): Future[HttpResponse] = {
    val req = requestToOk(request)
    val promise = Promise[HttpResponse]
    client.newCall(req).enqueue(new okhttp3.Callback {
      override def onResponse(call: okhttp3.Call, res: okhttp3.Response): Unit = {
        val response = responseFromOk(res)
        promise.success(response)
      }

      override def onFailure(call: okhttp3.Call, exc: IOException): Unit = promise.failure(exc)
    })
    JVMHttpClientImplementation.process(promise.future)(executionContext)
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
    }.getOrElse {
      if (request.method != HttpMethod.Get) {
        okhttp3.RequestBody.create(None.orNull, "")
      } else {
        None.orNull
      }
    }

    // Method
    r.method(request.method.value, body).header("Content-Length", Option(body).map(_.contentLength().toString).getOrElse("0"))
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
      } else if (contentToBytes(contentType, contentLength)) {
        Content.bytes(responseBody.bytes(), contentType)
      } else {
        val file = File.createTempFile("youi", "client", config.saveDirectory.toFile)
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


  override def content2String(content: Content): String = content match {
    case c: StringContent => c.value
    case c: BytesContent => String.valueOf(c.value)
    case c: FileContent => IO.stream(c.file, new StringBuilder).toString
    case _ => throw new RuntimeException(s"$content not supported")
  }

  protected def contentToBytes(contentType: ContentType, contentLength: Option[Long]): Boolean = {
    contentLength.exists(l => l > 0L && l < 512000L)
  }

  protected def content2Bytes(content: Content): Array[Byte] = content match {
    case c: StringContent => c.value.getBytes("UTF-8")
    case c: BytesContent => c.value
    case c: FileContent => IO.stream(c.file, new StringBuilder).toString.getBytes("UTF-*")
    case _ => throw new RuntimeException(s"$content not supported")
  }

  def logStats(): Unit = {
    val g = JVMHttpClientImplementation
    scribe.info(s"HttpClient stats - Pool[active: ${config.connectionPool.active}, idle: ${config.connectionPool.idle}, total: ${config.connectionPool.total}], Global[active: ${g.active}, successful: ${g.successful}, failure: ${g.failure}, total: ${g.total}]")
  }
}

object JVMHttpClientImplementation {
  private[client] val _total = new AtomicLong(0L)
  private[client] val _active = new AtomicLong(0L)
  private[client] val _successful = new AtomicLong(0L)
  private[client] val _failure = new AtomicLong(0L)

  private[client] def process(future: Future[HttpResponse])(implicit executionContext: ExecutionContext): Future[HttpResponse] = {
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