package io.youi.example.ui

import io.youi.example.screen.ExampleScreen

import scala.concurrent.{ExecutionContext, Future}

trait UIExampleScreen extends ExampleScreen {
  def name: String

  implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    UI.title := name
  }
}