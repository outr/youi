package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.{LazyUpdate, dom}
import io.youi.style.Theme
import org.scalajs.dom.html
import org.scalajs.dom.raw.CanvasRenderingContext2D

abstract class CanvasComponent extends Image {
  private val canvas = dom.create[html.Canvas]("canvas")
  private val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  private val pixiTexture: PIXI.Texture = PIXI.Texture.fromCanvas(canvas)

  texture := new Texture(pixiTexture)

  override protected def defaultTheme: Theme = CanvasComponent

  val reDraw = LazyUpdate {
    draw(context)
    pixiTexture.update()
    invalidate()
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

  protected def draw(context: CanvasRenderingContext2D): Unit

  override def update(delta: Double): Unit = {
    super.update(delta)

    reDraw.update()
  }
}

object CanvasComponent extends Theme(Image)