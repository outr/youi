package io.youi.canvas

import com.outr.pixijs.PIXI.SystemRenderer
import io.youi.hypertext.Canvas
import reactify._
import com.outr.pixijs._
import io.youi._

import scala.concurrent.Future

class CanvasRenderer(canvas: Canvas) extends Container {
  val running: Var[Boolean] = Var(false)

  scribe.info(s"Initializing: ${canvas.size.width.toInt}x${canvas.size.height.toInt}")

  val systemRenderer: SystemRenderer = PIXI.autoDetectRenderer(canvas.size.width.toInt, canvas.size.height.toInt, new RendererOptions {
    backgroundColor = 0x1099bb
    view = canvas.element
    autoResize = true
    antialias = true
  })

  canvas.delta.attach(render)

  protected def render(delta: Double): Unit = if (running()) {
    systemRenderer.render(displayObject)
  }

  def start(): Unit = running := true
  def stop(): Unit = running := false
}

object CanvasRenderer {
  var PixiJSURL: String = PIXI.Info.cdn
  lazy val Loaded: Future[Unit] = dom.addScript(PixiJSURL)

  def apply(canvas: Canvas): CanvasRenderer = new CanvasRenderer(canvas)
}