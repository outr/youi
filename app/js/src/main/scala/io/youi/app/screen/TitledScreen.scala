package io.youi.app.screen

import io.youi.ui

import scala.concurrent.ExecutionContext.Implicits.global

trait TitledScreen extends Screen {
  protected def title: String

  override protected def activate() = super.activate().map(_ => ui.title := title)
}