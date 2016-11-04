package io.youi.server.handler

import io.youi.http.{CacheControl, Content, Headers, HttpRequest, HttpResponse, Status}

class SenderHandler private(content: Content, contentType: String, length: Option[Long], caching: CachingManager) extends HttpHandler {
  override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = {
    val contentLength = length.getOrElse(content.length)
    val u = response
      .withHeader(Headers.`Content-Type`(contentType))
      .withHeader(Headers.`Content-Length`(contentLength))
      .withContent(content)
    caching.handle(request, u)
  }
}

object SenderHandler {
  def apply(content: Content, contentType: String, length: Option[Long] = None, caching: CachingManager = CachingManager.NotCached): SenderHandler = {
    new SenderHandler(content, contentType, length, caching)
  }
}

sealed trait CachingManager extends HttpHandler

object CachingManager {
  case object NotCached extends CachingManager {
    override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = {
      response.withHeader(Headers.`Cache-Control`(CacheControl.NoCache, CacheControl.NoStore))
    }
  }
  case class LastModified(publicCache: Boolean = true) extends CachingManager {
    val visibility = if (publicCache) CacheControl.Public else CacheControl.Private

    override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = response.content match {
      case Some(content) => {
        val ifModifiedSince = Headers.Request.`If-Modified-Since`.value(request.headers).getOrElse(0L)
        if (ifModifiedSince == content.lastModified) {
          println("Not modified!")
          response.copy(status = Status.NotModified, headers = Headers.empty, content = None)
        } else {
          println(s"Modified: $ifModifiedSince")
          response
            .withHeader(Headers.`Cache-Control`(visibility))
            .withHeader(Headers.Response.`Last-Modified`(content.lastModified))
        }
      }
      case None => response
    }
  }
  case class MaxAge(seconds: Long) extends CachingManager {
    override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = {
      response
        .withHeader(Headers.`Cache-Control`(CacheControl.MaxAge(seconds)))
    }
  }
}