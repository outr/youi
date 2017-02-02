package io.youi.example
import java.io.File

import io.youi.app.MultiPage
import io.youi.http._
import io.youi.stream._

object GeneralPages extends ExamplePage with MultiPage {
  override def pages: List[String] = List("login.html", "profile.html", "dashboard.html", "example.html")

  override def page2Resource(page: String) = new File(s"src/main/web/$page")

  override def scalaJSFunction: Option[String] = Some("io.youi.example.ClientExampleApplication().main")

  override def deltas(httpConnection: HttpConnection): List[Delta] = super.deltas(httpConnection) ::: List(
    Delta.ReplaceContent(ById("heading"), "Modified Heading"),
    Delta.ReplaceContent(ByTag("title"), "Modified Example Title"),
    Delta.ReplaceContent(ById("list"), "")
  )
}