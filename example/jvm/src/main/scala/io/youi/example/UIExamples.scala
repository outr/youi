package io.youi.example

import java.io.File

import io.youi.app.MatcherPage
import io.youi.http.{HttpConnection, path}
import io.youi.net.URLMatcher

object UIExamples extends ExamplePage with MatcherPage {
  override protected def matcher: URLMatcher = path.matches("/examples/.*[.]html")

  override protected def resource(httpConnection: HttpConnection): Option[File] = {
    Some(new File("src/main/web/ui-examples.html"))
  }

  override protected def scalaJSFunction: Option[String] = Some("examples")
}