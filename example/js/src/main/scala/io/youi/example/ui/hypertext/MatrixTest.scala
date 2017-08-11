package io.youi.example.ui.hypertext

import io.youi.drawable.Context
import io.youi.{AnimationFrame, CanvasDrawable, Color, dom, ui}
import io.youi.hypertext.{Button, Canvas}
import io.youi.spatial._
import org.scalajs.dom._

import scala.concurrent.Future

object MatrixTest extends HTMLScreen {
  override def name: String = "Matrix Test"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val c = new io.youi.component.Component {
      val canvas = drawable.asInstanceOf[CanvasDrawable].canvas
      canvas.style.backgroundColor = "lightblue"
      document.body.appendChild(canvas)

      size.width := 200.0
      size.height := 200.0

      override def draw(context: Context): Unit = {
        super.draw(context)

        scribe.info(s"Drawing component!")
      }
    }
    AnimationFrame.delta.attach(c.update)

//    val image = dom.create[html.Image]("img")
//    val canvas = new Canvas {
//      backgroundColor := Color.AliceBlue
//      size.width := 500.0
//      size.height := 500.0
//    }
//    container.children += canvas
//
//    val parentMatrix = Matrix3.Identity.translate(100.0, 100.0)
//    var matrix = Matrix3.Identity
//    val array = new Array[Double](9)
//
//    def draw(): Unit = {
//      val ctx = canvas.element.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
//      ctx.clearRect(0.0, 0.0, 500.0, 500.0)
//      matrix.fromArray(array)
//      ctx.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
//      ctx.drawImage(image, 0.0, 0.0)
//    }
//
//    image.src = "/images/icon.png"
//    image.addEventListener("load", (evt: Event) => {
//      draw()
//    })
//
//    container.children += new Button {
//      text := "Say 'Hello World'"
//      position.center := ui.position.center
//      position.middle := ui.position.middle
//
//      event.click.attach { _ =>
//        matrix = matrix
//          .*(parentMatrix)
//          .translate(150.0, 150.0)
//          .scale(0.5, 0.5)
//          .translate(100.0, 100.0)
//          .rotate(180.degrees)
//          .translate(-100.0, -100.0)
//        draw()
//      }
//    }
  }

  override def path: String = "/examples/html/matrix.html"
}