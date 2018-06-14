package io.youi.example

import io.youi.app._
import io.youi.http._
import io.youi.net._
import io.youi.server.handler.{CachingManager, ProxyCache}
import io.youi.server.dsl._

object ServerExampleApplication extends ExampleApplication with ServerApplication {
  val generalPages: Page = page(GeneralPages)

  override protected def applicationBasePath = s"app/youi-example"
  override protected def applicationJSBasePath = s"/app/example"

  override def start(): Unit = {
    // TODO: add support to `Connection` to add deltas so they may all be processed at the end
    proxies += ProxyHandler(path.exact("/proxy.html")) { url =>
      URL("http://google.com").copy(path = url.path)
    }
    handler(
      filters(
        path"/hello.txt" / CachingManager.MaxAge(120L) / "Hello, World!".withContentType(ContentType.`text/plain`),
        combined.any(
          path.matches("/examples/.*[.]html"),
          path.exact("/ui-examples.html")
        ) / Application / ProxyCache.delta("/cache") / ServerApplication.AppTemplate,
        path"/cookies.html" / CookiesExample,
        path"/session.html" / SessionExample,
        path"/cache" / ProxyCache(),
        ClassLoaderPath(pathTransform = (path: String) => s"content$path") / CachingManager.LastModified(),
        path.startsWith("/app") / ClassLoaderPath()
      )
    )

    super.start()
  }
}