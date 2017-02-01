package io.youi.example

import io.youi.app.{Page, ScalaJSConfig}

trait ExamplePage extends Page {
  def scalaJSFunction: String

  override def scalaJSConfig: Option[ScalaJSConfig] = {
    Some(ScalaJSConfig("/app/youi-server-example-fastopt.js", scalaJSFunction))
  }
}
