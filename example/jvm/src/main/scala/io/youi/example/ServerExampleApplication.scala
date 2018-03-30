package io.youi.example

import io.youi.app._
import io.youi.http._
import io.youi.net._
import io.youi.server.handler.CachingManager
import io.youi.server.dsl._

object ServerExampleApplication extends ExampleApplication with ServerApplication {
  val uiExamples: Page = page(UIExamples)
  val generalPages: Page = page(GeneralPages)

  override protected def applicationBasePath = s"app/youi-example"
  override protected def applicationJSBasePath = s"/app/example"

  override def start(): Unit = {
    proxies += ProxyHandler(path.exact("/proxy.html")) { url =>
      URL("http://google.com").copy(path = url.path)
    }
    handler(
      filters(
        path"/hello.txt" ->
          CachingManager.MaxAge(120L) ->
            "Hello, World!".withContentType(ContentType.`text/plain`),
        path"/bootstrap.html" ->
          Application ->
            ServerApplication.BootstrapTemplate,
        path"/cookies.html" ->
          CookiesExample,
        path"/session.html" ->
          SessionExample,
        ClassLoaderPath(pathTransform = (path: String) => s"content$path") -> CachingManager.LastModified(),
        path.startsWith("/app") -> ClassLoaderPath()
      )
    )

    super.start()
  }
}