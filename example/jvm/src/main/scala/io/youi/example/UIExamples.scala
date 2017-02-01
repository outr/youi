package io.youi.example

import java.io.File

import io.youi.http.path
import io.youi.net.URLMatcher

object UIExamples extends ExamplePage {
  override def matchers: List[URLMatcher] = List(path.matches("/examples/.*[.]html"))

  override def resource: File = new File("src/main/web/ui-examples.html")

  override def scalaJSFunction: String = "examples"
}