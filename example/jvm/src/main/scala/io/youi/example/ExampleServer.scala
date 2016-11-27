package io.youi.example

import java.io.File

import io.youi.http._
import io.youi.net.ContentType
import io.youi.net.URLMatcher._
import io.youi.server.UndertowServer
import io.youi.server.handler.{CachingManager, HttpHandler, SenderHandler}

object ExampleServer extends UndertowServer {
  def main(args: Array[String]): Unit = {
    handler.matcher(path.exact("/hello.txt")).caching(CachingManager.MaxAge(120L)).resource {
      Content.string("Hello World!", ContentType.`text/plain`)
    }
    handler.matcher(path.exact("/test.txt")).caching(CachingManager.LastModified()).resource {
      new File("src/main/web/test.txt")
    }
    handler.matcher(path.exact("/websocket.html")).caching(CachingManager.LastModified()).resource {
      new File("src/main/web/websocket.html")
    }
    handler.matcher(path.exact("/cookies.html")).wrap(CookiesExample)
    handler.matcher(path.exact("/web-socket-example")).wrap(WebSocketExample)
    handler.matcher(path.exact("/proxy.html")).wrap(ProxyExample)
    handler.matcher(path.exact("/session.html")).wrap(SessionExample)
    handler.matcher(path.exact("/communicator")).wrap(ServerExampleCommunicator)
    handler.caching(CachingManager.LastModified()).classLoader("", (path: String) => path.substring(1))

    start()
  }
}