package io.youi.component

import com.outr.pixijs._
import io.youi.component.event._
import io.youi._
import io.youi.hypertext.Canvas
import io.youi.hypertext.style.ColorProperties
import reactify.{Channel, Val, Var}

class Renderer private(val canvas: Canvas) extends Container {
  private val systemRenderer: PIXI.SystemRenderer = PIXI.autoDetectRenderer(
    options = new RendererOptions {
      width = canvas.size.width.toInt
      height = canvas.size.height.toInt
      view = canvas.element
      transparent = true
      autoResize = true
      antialias = true
      forceCanvas = false
    }
  )

  override lazy val event: RendererEvents = new RendererEvents(this)

  event.pointer.move.attach { evt =>
    Mouse.x.asInstanceOf[Var[Double]] := evt.globalX
    Mouse.y.asInstanceOf[Var[Double]] := evt.globalY
  }
  canvas.event.mouse.wheel.attach { evt =>
    val mode: DeltaMode = evt.deltaMode match {
      case 0x00 => DeltaMode.Pixel
      case 0x01 => DeltaMode.Line
      case 0x02 => DeltaMode.Page
    }
    val d = WheelDelta(evt.deltaX, evt.deltaY, evt.deltaZ, mode)
    Mouse.wheel := d
  }

  val renderMode: Var[RenderMode] = Var(RenderMode.OnChange)
  val backgroundColor: ColorProperties = canvas.backgroundColor

  override lazy val parentRenderer: Val[Option[Renderer]] = Val(Some(this))

  private val changeRenderer = LazyUpdate(systemRenderer.render(instance))

  size.width := canvas.size.width()
  size.height := canvas.size.height()

  canvas.delta.attach(update)

  override val globalVisibility: Var[Boolean] = visible

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
    def down: Channel[KeyEvent] = ui.event.key.down
    def press: Channel[KeyEvent] = ui.event.key.press
    def up: Channel[KeyEvent] = ui.event.key.up
  }
}

sealed trait RenderMode

object RenderMode {
  case object OnChange extends RenderMode
  case object EveryFrame extends RenderMode
}