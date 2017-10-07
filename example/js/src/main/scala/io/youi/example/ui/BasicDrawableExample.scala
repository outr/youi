package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.paint.Stroke

object BasicDrawableExample extends UIExampleScreen with UIScreen {
  override def name: String = "Basic Drawable"

  private val rectangle = new Drawable {
    override def draw(context: Context, x: Double, y: Double): Unit = {
      context.rect(100.0, 100.0, 250.0, 250.0)
      context.fill(Color.Red, apply = true)
    }
  }

  private val basicText = new Drawable {
    override def draw(context: Context, x: Double, y: Double): Unit = {
      context.fill(Color.Blue, apply = false)
      context.setFont("Arial", 96.0, "normal", "normal", "normal")
      context.fillText("Testing", 200.0, 200.0)
    }
  }

//  override protected val drawable: Future[Group] = Future.successful(Group(rectangle, text))
  override protected val drawable = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
    val text = font("Testing", 96.0)
    val text2 = new Drawable {
      override def draw(context: Context, x: Double, y: Double): Unit = {
        text.draw(context, x + 200.0, y + 300.0)
        context.fill(Color.Green, apply = true)
        context.stroke(Stroke(Color.Black, 2.0), apply = true)
      }
    }
    Group(rectangle, basicText, text2)
  }

  override def path: String = "/examples/basic-drawable.html"
}