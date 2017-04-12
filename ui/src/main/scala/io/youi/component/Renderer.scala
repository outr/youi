package io.youi.component

import com.outr.pixijs._
import io.youi.component.event.Events
import io.youi.{Color, dom}
import io.youi.hypertext.Canvas
import org.scalajs.dom.KeyboardEvent
import reactify.{Channel, Var}

import scala.concurrent.Future

class Renderer(val canvas: Canvas) extends Container {
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

  val backgroundColor: Var[Color] = Var.bound(Color.White, (c: Color) => systemRenderer.backgroundColor = c.long)

  size.width := canvas.size.width()
  size.height := canvas.size.height()

  canvas.delta.attach(update)

  override def update(delta: Double): Unit = {
    super.update(delta)

    systemRenderer.render(instance)
  }

  override protected def updateTransform(): Unit = {
    super.updateTransform()

    systemRenderer.resize(math.round(size.width()).toInt, math.round(size.height()).toInt)
    canvas.updateSize()
  }
}

object Renderer {
  var PixiJSURL: String = PIXI.Info.cdn
  lazy val Loaded: Future[Unit] = dom.addScript(PixiJSURL)

  def apply(canvas: Canvas): Renderer = new Renderer(canvas)
}

class RendererEvents(renderer: Renderer) extends Events(renderer) {
  object key {
    def down: Channel[KeyboardEvent] = renderer.canvas.event.key.down
    def press: Channel[KeyboardEvent] = renderer.canvas.event.key.press
    def up: Channel[KeyboardEvent] = renderer.canvas.event.key.up
  }
}