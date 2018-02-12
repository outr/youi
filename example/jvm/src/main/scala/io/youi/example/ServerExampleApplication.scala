package io.youi.example

import io.youi.app._
import io.youi.http._
import io.youi.net.{ContentType, URL}
import io.youi.server.handler.CachingManager

object ServerExampleApplication extends ExampleApplication with ServerApplication {
  val uiExamples: Page = page(UIExamples)
  val generalPages: Page = page(GeneralPages)

  override protected def applicationBasePath = s"app/youi-example"
  override protected def applicationJSBasePath = s"/app/example"

  override def start(): Unit = {
    handler.matcher(path.exact("/hello.txt")).caching(CachingManager.MaxAge(120L)).resource {
      Content.string("Hello World!", ContentType.`text/plain`)
    }
    handler.matcher(path.exact("/cookies.html")).wrap(CookiesExample)
    handler.matcher(path.exact("/web-socket-example")).wrap(WebSocketExample)
    proxies += ProxyHandler(path.exact("/proxy.html")) { url =>
      URL("http://google.com").copy(path = url.path)
    }
    handler.matcher(path.exact("/session.html")).wrap(SessionExample)
    handler.caching(CachingManager.LastModified()).classLoader("", (path: String) => s"content$path")
    handler.caching(CachingManager.LastModified()).matcher(path.startsWith("/app")).classLoader()

    super.start()
  }
}