package io.youi.example
import java.io.File

import io.youi.http._
import io.youi.net.URLMatcher

object GeneralPages extends ExamplePage {
  override def matcher: URLMatcher = combined.any(
    path.exact("/dashboard.html"),
    path.exact("/login.html"),
    path.exact("/profile.html"),
    path.exact("/example.html")
  )

  override def resource(httpConnection: HttpConnection): File = new File("src/main/web/template.html")

  override def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")
}