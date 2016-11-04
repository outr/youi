package io.youi.example

import java.io.File

import io.youi.http.{HttpRequest, HttpResponse}
import io.youi.net.URLMatcher
import io.youi.server.UndertowServer
import io.youi.server.handler.{CachingManager, HttpHandler, SenderHandler}

object ExampleServer extends UndertowServer {
  def main(args: Array[String]): Unit = {
    handlers.add(URLMatcher.path.exact("/hello.txt"))(SenderHandler("Hello, World!", "text/plain", caching = CachingManager.MaxAge(120L)))
    handlers.add(URLMatcher.path.exact("/test.txt"))(SenderHandler(new File("src/main/web/test.txt"), "text/plain", caching = CachingManager.LastModified()))

    start()
  }
}