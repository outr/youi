package io.youi.client

import cats.effect.IO
import io.youi.ajax.{AjaxAction, AjaxRequest}
import io.youi.http.content._
import io.youi.http.{Headers, HttpRequest, HttpResponse, HttpStatus}
import io.youi.net.ContentType

import scala.util.{Failure, Success, Try}

class JSHttpClientImplementation(config: HttpClientConfig) extends HttpClientImplementation(config) {
  private val HeaderRegex = """(.+)[:](.+)""".r

  override def send(request: HttpRequest): IO[Try[HttpResponse]] = {
    val manager = config.connectionPool.asInstanceOf[JSConnectionPool].manager
    val ajaxRequest = new AjaxRequest(
      url = request.url,
      data = request.content.map(content2String),
      timeout = 0,
      headers = request.headers.map.flatMap(t => t._2.map(value => t._1 -> value)),
      withCredentials = true,
      responseType = ""
    )
    val action = new AjaxAction(ajaxRequest)
    manager.enqueue(action).map {
      case Failure(err) => Failure(err)
      case Success(xmlHttpRequest) =>
        val headers: Map[String, List[String]] = xmlHttpRequest.getAllResponseHeaders().split('\n').map(_.trim).map {
          case HeaderRegex(key, value) => key.trim -> value.trim
          case s => throw new RuntimeException(s"Invalid Header: [$s]")
        }.groupBy(_._1).map {
          case (key, array) => key -> array.toList.map(_._2)
        }
        val content = xmlHttpRequest.responseType match {
          case null => None
          case _ => {
            val `type` = if (xmlHttpRequest.responseType == "") ContentType.`text/plain` else ContentType.parse(xmlHttpRequest.responseType)
            Some(Content.string(xmlHttpRequest.responseText, `type`))
          }
        }
        Success(HttpResponse(
          status = HttpStatus(xmlHttpRequest.status, xmlHttpRequest.statusText),
          headers = Headers(headers),
          content = content
        ))
    }
  }

  override def content2String(content: Content): String = content match {
    case c: StringContent => c.value
    case _ => throw new RuntimeException(s"$content not supported")
  }
}