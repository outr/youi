package io.youi.example.ui

import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.{Color, Context, Drawable, Renderer}

import scala.concurrent.Future

object BasicDrawableExample extends HTMLScreen {
  override def name: String = "Basic Drawable"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    Renderer.drawable := new Drawable {
      override def draw(context: Context): Unit = {
        context.rect(100.0, 100.0, 250.0, 250.0)
        context.fill(Color.Red, apply = true)
      }
    }
    Renderer.render()
    Renderer.visible := true
  }

  override def path: String = "/examples/basic-drawable.html"
}