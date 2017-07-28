package io.youi.example
import io.youi.app.MatcherPage
import io.youi.http._
import io.youi.net.URLMatcher
import io.youi.server.validation.Validator

object GeneralPages extends ExamplePage with MatcherPage {
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

  override protected def resource(httpConnection: HttpConnection): Option[Content] = Some(Content.classPath(s"content/templates${httpConnection.request.url.path.decoded}"))

  override protected def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")
}