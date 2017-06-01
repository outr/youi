package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.component.filter.CanvasFilter
import io.youi.{LazyUpdate, dom}
import io.youi.style.Theme
import org.scalajs.dom.html
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

abstract class CanvasComponent extends Image {
  private val canvas = dom.create[html.Canvas]("canvas")
  private val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  private val pixiTexture: PIXI.Texture = PIXI.Texture.fromCanvas(canvas)

  val filter: Var[Option[CanvasFilter]] = Var(None)

  texture := new Texture(pixiTexture)

  override protected def defaultTheme: Theme = CanvasComponent

  val reDraw = LazyUpdate {
    context.clearRect(0.0, 0.0, canvas.width.toDouble, canvas.height.toDouble)
    draw(context)
    filter().foreach { filter =>
      val imageData = context.getImageData(0.0, 0.0, canvas.width.toDouble, canvas.height.toDouble)
      filter(imageData)
      context.putImageData(imageData, 0, 0)
    }
    pixiTexture.update()
    super.updateTransform()
  }
  reDraw.flag()
  size.width.attachAndFire { d =>
    canvas.width = math.ceil(d).toInt
    reDraw.flag()
  }
  size.height.attachAndFire { d =>
    canvas.height = math.ceil(d).toInt
    reDraw.flag()
  }
  filter.on(reDraw.flag())

  protected def draw(context: CanvasRenderingContext2D): Unit

  override def update(delta: Double): Unit = {
    super.update(delta)

    reDraw.update()
  }

  override protected def updateTransform(): Unit = reDraw.flag()
}

object CanvasComponent extends Theme(Image)