package io.youi.example
import java.io.File

import io.youi.app.MatcherPage
import io.youi.http._
import io.youi.net.URLMatcher

object GeneralPages extends ExamplePage with MatcherPage {
  override protected def matcher: URLMatcher = combined.any(
    path.exact("/dashboard.html"),
    path.exact("/login.html"),
    path.exact("/profile.html"),
    path.exact("/example.html")
  )

  override protected def resource(httpConnection: HttpConnection): Option[File] = Some(new File("src/main/web/template.html"))

  override protected def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")
}