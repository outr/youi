package io.youi.example

import java.io.File

import io.youi.app.{Page, ServerApplication}
import io.youi.http._
import io.youi.net.ContentType
import io.youi.server.UndertowServer
import io.youi.server.handler.{CachingManager, HttpHandler}
import io.youi.stream.{ById, Delta}

object ServerExampleApplication extends UndertowServer with ExampleApplication with ServerApplication {
  config.clearListeners().addHttpListener("0.0.0.0")

  val uiExamples: HttpHandler = page(UIExamples)
  val dashboard: HttpHandler = page(DashboardPage)

  handler.matchers(path.exact("/hello.txt")).caching(CachingManager.MaxAge(120L)).resource {
    Content.string("Hello World!", ContentType.`text/plain`)
  }
  handler.matchers(path.exact("/test.txt")).caching(CachingManager.LastModified()).resource {
    new File("src/main/web/test.txt")
  }
  handler.matchers(path.exact("/websocket.html")).caching(CachingManager.LastModified()).resource {
    new File("src/main/web/websocket.html")
  }
  handler.matchers(path.exact("/cookies.html")).wrap(CookiesExample)
  handler.matchers(path.exact("/web-socket-example")).wrap(WebSocketExample)
  handler.matchers(path.exact("/proxy.html")).proxy(ProxyExample)
  handler.matchers(path.exact("/session.html")).wrap(SessionExample)
  handler.caching(CachingManager.LastModified()).classLoader("", (path: String) => s"content$path")
  handler.caching(CachingManager.LastModified()).classLoader("app", (path: String) => path.drop(4))

  def main(args: Array[String]): Unit = {
    start()
  }
}