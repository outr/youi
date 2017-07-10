package io.youi.example

import io.youi.app.MatcherPage
import io.youi.http.{Content, HttpConnection, combined, path}
import io.youi.net.URLMatcher

object UIExamples extends ExamplePage with MatcherPage {
  override protected def matcher: URLMatcher = combined.any(
    path.matches("/examples/.*[.]html"),
    path.exact("/ui-examples.html")
  )

  override protected def resource(httpConnection: HttpConnection): Option[Content] = {
    Some(Content.classPath("content/templates/ui-examples.html"))
  }

  override protected def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")
}