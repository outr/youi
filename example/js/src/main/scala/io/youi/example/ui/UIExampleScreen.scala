package io.youi.example.ui

import io.youi.UI
import io.youi.example.screen.ExampleScreen
import io.youi.hypertext.Container

import scala.concurrent.{ExecutionContext, Future}

trait UIExampleScreen extends ExampleScreen {
  def name: String

  implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  lazy val container: Container = Container.cached(content)

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    UI.title := name
  }
}