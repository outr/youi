package io.youi.server.handler

import java.io.File

import io.youi.http._
import io.youi.net.{URL, URLMatcher}
import io.youi.server.Server

case class HttpHandlerBuilder(server: Server,
                              urlMatcher: Option[URLMatcher] = None,
                              cachingManager: CachingManager = CachingManager.Default) {
  def matcher(urlMatchers: URLMatcher*): HttpHandlerBuilder = if (urlMatchers.isEmpty) {
    copy(urlMatcher = None)
  } else if (urlMatchers.tail.isEmpty) {
    copy(urlMatcher = Some(urlMatchers.head))
  } else {
    copy(urlMatcher = Some(combined(urlMatchers: _*)))
  }

  def caching(cachingManager: CachingManager): HttpHandlerBuilder = copy(cachingManager = cachingManager)

  def resource(f: => Content): HttpHandler = resource((_: URL) => Some(f))

  def resource(f: URL => Option[Content]): HttpHandler = {
    val handler: HttpHandler = (connection: HttpConnection) => {
      if (urlMatcher.forall(_.matches(connection.request.url))) {
        f(connection.request.url).foreach { content =>
          SenderHandler(content, caching = cachingManager).handle(connection)
        }
      }
    }
    server.handlers += handler
    handler
  }

  def file(directory: File, pathTransform: String => String = (s: String) => s): HttpHandler = {
    val handler: HttpHandler = (connection: HttpConnection) => {
      if (urlMatcher.forall(_.matches(connection.request.url))) {
        val path = pathTransform(connection.request.url.path.encoded)
        val file = new File(directory, path)
        if (file.exists()) {
          SenderHandler(Content.file(file), caching = cachingManager).handle(connection)
        }
      }
    }
    server.handlers += handler
    handler
  }

  def classLoader(directory: String, pathTransform: String => String = (s: String) => s): HttpHandler = {
    val dir = if (directory.endsWith("/")) {
      directory.substring(directory.length - 1)
    } else {
      directory
    }
    val handler: HttpHandler = (connection: HttpConnection) => {
      if (urlMatcher.forall(_.matches(connection.request.url))) {
        val path = pathTransform(connection.request.url.path.encoded)
        Option(getClass.getClassLoader.getResource(s"$dir$path")).foreach { url =>
          SenderHandler(Content.classPath(url), caching = cachingManager).handle(connection)
        }
      }
    }
    server.handlers += handler
    handler
  }

  def wrap(handler: HttpHandler): HttpHandler = {
    val wrapper = new HttpHandler {
      override def handle(connection: HttpConnection): Unit = {
        if (urlMatcher.forall(_.matches(connection.request.url))) {
          handler.handle(connection)
        }
      }
    }
    server.handlers += wrapper
    handler
  }
}