package io.youi.example

import java.io.File

import io.youi.http.{HttpConnection, path}
import io.youi.net.URLMatcher

object UIExamples extends ExamplePage {
  override def matcher: URLMatcher = path.matches("/examples/.*[.]html")

  override def resource(httpConnection: HttpConnection): File = new File("src/main/web/ui-examples.html")

  override def scalaJSFunction: Option[String] = Some("examples")
}