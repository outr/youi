package io.youi.example

import io.youi.app.{Page, ScalaJSConfig}

trait ExamplePage extends Page {
  def scalaJSFunction: Option[String] = None

  override def scalaJSConfig: Option[ScalaJSConfig] = scalaJSFunction map { f =>
    ScalaJSConfig("/app/youi-server-example-fastopt.js", f)
  }
}
