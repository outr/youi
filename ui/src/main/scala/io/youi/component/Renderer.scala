package io.youi.component

import com.outr.pixijs._
import io.youi.dom
import io.youi.hypertext.Canvas

import scala.concurrent.Future

class Renderer(canvas: Canvas) extends Container {
  private val systemRenderer: PIXI.SystemRenderer = PIXI.autoDetectRenderer(
    width = canvas.size.width.toInt,
    height = canvas.size.height.toInt,
    options = new RendererOptions {
      backgroundColor = 0x1099bb
      view = canvas.element
      autoResize = true
      antialias = true
    },
    noWebGL = false
  )

  canvas.delta.attach(update)

  override def update(delta: Double): Unit = {
    super.update(delta)

    systemRenderer.render(instance)
  }

  override protected def updateSize(): Unit = {
    super.updateSize()

    scribe.info(s"Resizing to: ${size.width()}x${size.height()}")
//    canvas.size.width := size.width()
//    canvas.size.height := size.height()
//    canvas.element.width = size.width().toInt
//    canvas.element.height = size.height().toInt
//    systemRenderer.width = size.width().toInt
//    systemRenderer.height = size.height().toInt
    systemRenderer.resize(math.round(size.width()).toInt, math.round(size.height()).toInt)
    canvas.updateSize()
  }
}

object Renderer {
  var PixiJSURL: String = PIXI.Info.cdn
  lazy val Loaded: Future[Unit] = dom.addScript(PixiJSURL)

  def apply(canvas: Canvas): Renderer = new Renderer(canvas)
}