package io.youi.example

import io.youi.app._
import io.youi.http._
import io.youi.http.content.Content
import io.youi.net._
import io.youi.server.handler.{CachingManager, LanguageSupport}
import io.youi.server.dsl._
import profig.JsonUtil

import scala.concurrent.Future
import scribe.Execution.global

object ServerExampleApplication extends ExampleApplication with ServerConnectedApplication[ExampleConnection] {
  val generalPages: Page = page(GeneralPages)

  case class Greeting(message: String, name: String)

  override def createConnection(): ExampleConnection = new ServerConnection

  override protected def init(): Future[Unit] = {
    super.init().map { _ =>
      // TODO: add support to `Connection` to add deltas so they may all be processed at the end
      proxies += ProxyHandler(path.exact("/proxy.html")) { url =>
        URL("http://google.com").copy(path = url.path)
      }
      handler(
        filters(
          path"/" / redirect(path"/ui-examples.html"),
          path"/hello.txt" / CachingManager.MaxAge(120L) / "Hello, World!".withContentType(ContentType.`text/plain`),
          path"/hello.json" / Content.json(JsonUtil.toJson(Greeting("Hello", "World"))),
          combined.any(
            path.matches("/examples/.*[.]html"),
            path.exact("/ui-examples.html")
          ) / Application / ServerApplication.AppTemplate,
          path"/cookies.html" / CookiesExample,
          path"/session.html" / SessionExample,
          ClassLoaderPath(pathTransform = (path: String) => s"content$path") / CachingManager.LastModified(),
          path.startsWith("/app") / ClassLoaderPath()
        )
      )
      handlers += new LanguageSupport()
    }
  }
}