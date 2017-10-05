package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen

object BasicDrawableExample extends UIExampleScreen with UIScreen {
  override def name: String = "Basic Drawable"

  private val rectangle = new Drawable {
    override def draw(context: Context): Unit = {
      scribe.info("Rendering!")
      context.rect(100.0, 100.0, 250.0, 250.0)
      context.fill(Color.Red, apply = true)
    }
  }

  private val text = new Drawable {
    override def draw(context: Context): Unit = {
      context.fill(Color.Blue, apply = false)
      context.setFont("Arial", 96.0, "normal", "normal", "normal")
      context.fillText("Testing", 200.0, 200.0)
    }
  }

  override protected val drawable: Group = new Group {
    override protected val elements: List[Drawable] = List(rectangle, text)
  }

  override def path: String = "/examples/basic-drawable.html"
}