package io.youi.example

import java.io.File

import io.youi.http._
import io.youi.net.ContentType
import io.youi.net.URLMatcher._
import io.youi.server.UndertowServer
import io.youi.server.handler.{CachingManager, HttpHandler, SenderHandler}

object ExampleServer extends UndertowServer {
  def main(args: Array[String]): Unit = {
    handlers.add(path.exact("/hello.txt"))(SenderHandler(Content.string("Hello, World!", ContentType.`text/plain`), caching = CachingManager.MaxAge(120L)))
    handlers.add(path.exact("/test.txt"))(SenderHandler(new File("src/main/web/test.txt"), caching = CachingManager.LastModified()))
    handlers.add(path.exact("/websocket.html"))(SenderHandler(new File("src/main/web/websocket.html"), caching = CachingManager.LastModified()))
    handlers.add(path.exact("/cookies.html"))(CookiesExample)
    handlers.add(path.exact("/web-socket-example"))(WebSocketExample)
    handlers.add(path.exact("/proxy.html"))(ProxyExample)
    handlers.add(path.exact("/session.html"))(SessionExample)
    handlers.add(path.exact("/communicator"))(ServerExampleCommunicator)
    handler.caching(CachingManager.LastModified()).classLoader("", (path: String) => path.substring(1))

    start()
  }
}