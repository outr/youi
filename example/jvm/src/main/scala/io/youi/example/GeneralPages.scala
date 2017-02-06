package io.youi.example
import java.io.File

import io.youi.app.{MatcherPage, TemplatePage}
import io.youi.http._
import io.youi.net.URLMatcher
import io.youi.server.validation.Validator

object GeneralPages extends ExamplePage with TemplatePage {
  override protected def matcher: URLMatcher = combined.any(
    path.exact("/dashboard.html"),
    path.exact("/login.html"),
    path.exact("/profile.html"),
    path.exact("/communication.html")
  )

  override protected def validators(httpConnection: HttpConnection): List[Validator] = if (httpConnection.request.url.path.decoded == "/login.html") {
    super.validators(httpConnection)
  } else {
    super.validators(httpConnection) ::: List(AuthenticationExampleValidator)
  }

  override def templateFor(path: String): Option[File] = Some(new File("src/main/web/template.html"))

  override def partFor(path: String): Option[File] = Some(new File(s"src/main/web$path"))

  override protected def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")
}