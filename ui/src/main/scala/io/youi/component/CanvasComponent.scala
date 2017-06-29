package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.component.filter.CanvasFilter
import io.youi.theme.CanvasComponentTheme
import io.youi._
import org.scalajs.dom.html
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

abstract class CanvasComponent extends TextureComponent {
  protected val canvas: html.Canvas = dom.create[html.Canvas]("canvas")
  private val context = canvas.context
  private val pixiTexture: PIXI.Texture = PIXI.Texture.fromCanvas(canvas)

  val filter: Var[Option[CanvasFilter]] = Var(None)

  texture := new Texture(pixiTexture)

  override lazy val theme: Var[_ <: CanvasComponentTheme] = Var(CanvasComponent)

  val reDraw = LazyFuture({
    context.save()
    context.clearRect(0.0, 0.0, canvas.width.toDouble, canvas.height.toDouble)
    draw(context).map { _ =>
      filter().foreach { filter =>
        val imageData = context.getImageData(0.0, 0.0, canvas.width.toDouble, canvas.height.toDouble)
        filter(imageData)
        context.putImageData(imageData, 0, 0)
      }
      pixiTexture.update()
      context.restore()
      super.updateTransform()
    }
  }, automatic = false)
  reDraw.flag()
  size.width.attachAndFire { d =>
    canvas.width = math.ceil(d).toInt
    reDraw.flag()
  }
  size.height.attachAndFire { d =>
    canvas.height = math.ceil(d).toInt
    reDraw.flag()
  }

  // TODO: remove
  // Override defaults from Image
  scale.x := 1.0
  scale.y := 1.0

  filter.on(reDraw.flag())

  protected def draw(context: CanvasRenderingContext2D): Future[Unit]

  // TODO: don't re draw on transform change
  override protected def updateTransform(): Unit = reDraw.flag()

  override def update(delta: Double): Unit = {
    super.update(delta)
    reDraw.update()
  }
}

object CanvasComponent extends CanvasComponentTheme