package io.youi.example
import java.io.File

import io.youi.http._
import io.youi.net.URLMatcher
import io.youi.stream._

object DashboardPage extends ExamplePage {
  override def matchers: List[URLMatcher] = List(path.exact("/dashboard.html"))

  override def scalaJSFunction: String = "io.youi.example.ClientExampleApplication().main"

  override def resource: File = new File("src/main/web/dashboard.html")

  override def deltas(httpConnection: HttpConnection): List[Delta] = super.deltas(httpConnection) ::: List(
    Delta.ReplaceContent(ById("heading"), "Modified Heading")
  )
}