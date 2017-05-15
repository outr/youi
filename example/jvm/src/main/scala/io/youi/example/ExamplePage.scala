package io.youi.example

import io.youi.app.{Page, ScalaJSConfig}

trait ExamplePage extends Page {
  protected def scalaJSFunction: Option[String] = None

  override protected def scalaJSConfig: Option[ScalaJSConfig] = scalaJSFunction map { f =>
    ScalaJSConfig("/app/youi-server-example-fastopt.js", f, Some("/app/youi-server-example-jsdeps.js"))
  }
}
