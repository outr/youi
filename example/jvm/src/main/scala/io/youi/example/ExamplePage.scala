package io.youi.example

import io.youi.app.Page

trait ExamplePage extends Page {
  override protected def application = ServerExampleApplication
}