package io.youi.canvas

import com.outr.pixijs.PIXI.{SystemRenderer, Texture}
import io.youi.hypertext.Canvas
import reactify._
import com.outr.pixijs._
import io.youi._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class CanvasRenderer(canvas: Canvas) extends Container {
  val running: Var[Boolean] = Var(false)

  scribe.info(s"Initializing: ${canvas.size.width.toInt}x${canvas.size.height.toInt}")
  val systemRenderer: SystemRenderer = PIXI.autoDetectRenderer(canvas.size.width.toInt, canvas.size.height.toInt, new RendererOptions {
    backgroundColor = 0x1099bb
    view = canvas.element
    autoResize = true
    antialias = true
  })

  val texture: PIXI.Texture = PIXI.Texture.fromImage("/images/bunny.png")
  val bunny = new PIXI.Sprite(texture) {
    anchor.x = 0.5
    anchor.y = 0.5
    position.x = 200
    position.y = 150
  }
  displayObject.addChild(bunny)

  canvas.delta.attach(render)

  protected def render(delta: Double): Unit = if (running()) {
    systemRenderer.render(displayObject)
  }

  def start(): Unit = running := true
  def stop(): Unit = running := false
}

object CanvasRenderer {
  lazy val Loaded: Future[Unit] = dom.addScript(PIXI.Info.cdn)

  def apply(canvas: Canvas): CanvasRenderer = new CanvasRenderer(canvas)
}