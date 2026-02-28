package youi.example.ui

import rapid.Task
import youi.Color
import youi.component.DrawableView
import youi.drawable.{Group, TextDrawable, Transformation}
import youi.example.screen.UIExampleScreen
import youi._
import youi.font.{CanvasFont, CanvasText, GoogleFont}
import youi.image.Image
import youi.paint.{Border, GradientPaint, Paint, Stroke}
import spice.net._

class DrawableExample extends UIExampleScreen {
  override def title: String = "Drawable Example"
  override def path: URLPath = path"/examples/drawable.html"

  override def createUI(): Task[Unit] = for {
    image <- Image("/images/cuteness.jpg")
    fnt <- GoogleFont.`Lobster`.`regular`.load()
  } yield {
    val component: DrawableView = new DrawableView {
      border @= Border(Stroke(Color.Red, lineWidth = 2.0), radius = 5.0)
      width @= image.width
      height @= image.height
      position.center := container.size.center
      position.middle := container.size.middle

      val font: CanvasFont = CanvasFont(fnt.font.family, "normal", "normal", fnt.name)
      val text: CanvasText = CanvasText(font, "Hello, Lobster Font!", 36.0, Int.MaxValue, kerning = false)
      val paint: GradientPaint = Paint.horizontal(text.boundingBox.width).distributeColors(Color.Red, Color.Green, Color.Blue)
      val textDrawable = new TextDrawable(text, paint, Stroke(Color.White))
      drawable @= Group(
        image,
        Transformation(50.0, 25.0)(textDrawable)
      )
    }
    container.children += component
  }
}
