package io.youi.example

import io.youi.app._
import io.youi.http._
import io.youi.net.ContentType
import io.youi.server.UndertowServer
import io.youi.server.handler.CachingManager

object ServerExampleApplication extends UndertowServer with ExampleApplication with ServerApplication {
  val uiExamples: Page = page(UIExamples)
  val generalPages: Page = page(GeneralPages)

  handler.matcher(path.exact("/hello.txt")).caching(CachingManager.MaxAge(120L)).resource {
    Content.string("Hello World!", ContentType.`text/plain`)
  }
  handler.matcher(path.exact("/cookies.html")).wrap(CookiesExample)
  handler.matcher(path.exact("/web-socket-example")).wrap(WebSocketExample)
  handler.matcher(path.exact("/proxy.html")).proxy(ProxyExample)
  handler.matcher(path.exact("/session.html")).wrap(SessionExample)
  handler.caching(CachingManager.LastModified()).classLoader("", (path: String) => s"content$path")
  handler.caching(CachingManager.LastModified()).matcher(path.startsWith("/app")).classLoader()
}