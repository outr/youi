package io.youi.example.ui

import io.youi._
import io.youi.example.ui.hypertext.HTMLScreen

import scala.concurrent.Future

object BasicDrawableExample extends HTMLScreen {
  override def name: String = "Basic Drawable"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    Renderer.drawable := new Drawable {
      override def draw(context: Context): Unit = {
        context.rect(100.0, 100.0, 250.0, 250.0)
        context.fill(Color.Red, apply = true)
        context.fill(Color.Blue, apply = false)
        context.setFont("Arial", 96.0, "normal", "normal", "normal")
        context.fillText("Testing", 200.0, 200.0)
      }
    }
    Renderer.render()
    Renderer.visible := true
  }

  override def path: String = "/examples/basic-drawable.html"
}