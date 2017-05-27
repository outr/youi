package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.font.Font
import io.youi.component.shape.Path
import io.youi.component.{CanvasComponent, RenderMode}
import opentype.PathOptions
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Val

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"
  override def path: String = "/examples/text.html"

  override def createUI(): Unit = {
    renderer.renderMode := RenderMode.EveryFrame

    val font = Font.fromPath("/fonts/Pacifico.ttf")
    val text = "Hello, World!"
    val fontSize = 48.0

    container.children += new CanvasComponent {
      position.center := container.position.center
      position.middle := container.position.middle
      size.width := 400.0
      size.height := 200.0

      private var adjustX = 0.0
      private var adjustY = 0.0

      val path: Val[Option[Path]] = Val {
        font.internal().map { ot =>
          val path = ot.getPath(text, 0.0, 0.0, fontSize, new PathOptions {
            kerning = true
          })
          val box = path.getBoundingBox()
          val width = box.x2 - box.x1
          val height = box.y2 - box.y1
//          size.measured.width := width
//          size.measured.height := height
          adjustX = -box.x1
          adjustY = -box.y2
          try {
            Path(path.toPathData())
          } finally {
//            invalidate()
            reDraw.flag()
          }
        }
      }

      override protected def draw(context: CanvasRenderingContext2D): Unit = {
        context.fillStyle = "yellow"
        context.fillRect(0.0, 0.0, size.width(), size.height())
        context.fillStyle = "black"
        context.strokeStyle = "black"
//        context.font = "64px Arial"
//        context.fillText("Hello, World!", 20.0, 100.0)

        context.translate(adjustX, size.height() + adjustY)

        path().foreach { p =>
          p.draw(this, context)
        }
        context.fill()
      }
    }
//    val text = new BasicText {
//      value := "Hello, World!"
//      font.size := 48.0
//      fill := Color.DarkBlue
//      position.center := renderer.position.center
//      position.middle := renderer.position.middle
//    }
//    container.children += text
  }
}
