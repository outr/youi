package io.youi.example.ui.drawable

import io.youi.{Color, ui}
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.path.{Fill, Rectangle}
import io.youi.util.CanvasPool

import scala.concurrent.Future

object PerformanceExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Performance Example"

  override protected def drawable: Future[Drawable] = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
    val text = font("Testing", 96.0)
    val canvas = CanvasPool(text.boundingBox.width * ui.ratio, text.boundingBox.height * ui.ratio)
    val context = new Context(canvas, ui.ratio)
    text.draw(context, 0.0, 0.0)
    Fill.draw(context, Color.White)
    Group(
      Transformation(x = 100.0, y = 100.0)(
        Group(
          text,
          Fill(Color.Green)
        )
      ),
      Transformation(x = 100.0, y = 250.0)(
        Clipped(0.0, 0.0, text.boundingBox.width, text.boundingBox.height) {
          Group(
            new Drawable {
              override def draw(context: Context, x: Double, y: Double): Unit = {
                context.save()
                val scale = 1.0 / ui.ratio
                context.scale(scale, scale)
                context.drawCanvas(canvas)()
                context.restore()
                context.composite(Composite.SourceIn)
              }
            },
            Rectangle(0.0, 0.0, text.boundingBox.width, text.boundingBox.height, begin = true, close = true),
            Fill(Color.Blue)
          )
        }
      )
    )
  }

  override def path: String = "/examples/drawable/performance.html"
}