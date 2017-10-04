package io.youi.example.ui

import io.youi.Display
import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.hypertext.Button

import scala.concurrent.Future

object BasicDrawableExample extends HTMLScreen {
  override def name: String = "Basic Drawable"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    // TODO: add renderer and drawable to test drawing
  }

  override def path: String = "/examples/basic-drawable.html"
}