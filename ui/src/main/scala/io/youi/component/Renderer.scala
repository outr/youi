package io.youi.component

import com.outr.pixijs._
import io.youi.component.event.Events
import io.youi.{Color, LazyUpdate, dom}
import io.youi.hypertext.Canvas
import org.scalajs.dom.KeyboardEvent
import reactify.{Channel, Var}

import scala.concurrent.Future

class Renderer private(val canvas: Canvas) extends Container {
  private val systemRenderer: PIXI.SystemRenderer = PIXI.autoDetectRenderer(
    width = canvas.size.width.toInt,
    height = canvas.size.height.toInt,
    options = new RendererOptions {
      view = canvas.element
      backgroundColor = 0xffffff
      autoResize = true
      antialias = true
    },
    noWebGL = false
  )

  val renderMode: Var[RenderMode] = Var(RenderMode.OnChange)
  val backgroundColor: Var[Color] = prop(Color.White, (c: Color) => systemRenderer.backgroundColor = c.long, updatesRendering = true)

  private val changeRenderer = LazyUpdate(systemRenderer.render(instance))

  size.width := canvas.size.width()
  size.height := canvas.size.height()

  canvas.delta.attach(update)

  override def update(delta: Double): Unit = {
    super.update(delta)

    renderMode() match {
      case RenderMode.OnChange => changeRenderer.update()
      case RenderMode.EveryFrame => systemRenderer.render(instance)
    }
  }

  override protected def updateTransform(): Unit = {
    super.updateTransform()

    systemRenderer.resize(math.round(size.width()).toInt, math.round(size.height()).toInt)
    canvas.updateSize()
  }

  override def invalidate(): Unit = changeRenderer.flag()
}

object Renderer {
  def apply(canvas: Canvas): Renderer = new Renderer(canvas)
}

class RendererEvents(renderer: Renderer) extends Events(renderer) {
  object key {
    def down: Channel[KeyboardEvent] = renderer.canvas.event.key.down
    def press: Channel[KeyboardEvent] = renderer.canvas.event.key.press
    def up: Channel[KeyboardEvent] = renderer.canvas.event.key.up
  }
}

sealed trait RenderMode

object RenderMode {
  case object OnChange extends RenderMode
  case object EveryFrame extends RenderMode
}