package io.youi.component

import io.youi.{AnimationFrame, Drawable, HTMLEvents}
import org.scalajs.dom.{document, html}
import reactify._

class Renderer(canvas: html.Canvas) extends Container {
  override protected[youi] lazy val drawable: Drawable = new Drawable(canvas, swapCanvases = false)

  val htmlEvents: HTMLEvents = new HTMLEvents(canvas)
  override lazy val renderer: Val[Option[Renderer]] = Val(Some(this))

  canvas.style.position = "absolute"
  canvas.style.left = "0px"
  canvas.style.top = "0px"
  hide()

  document.body.appendChild(canvas)

  AnimationFrame.delta.attach(update)

  def apply(): html.Canvas = canvas

  def show(): Unit = canvas.style.display = "block"
  def hide(): Unit = canvas.style.display = "none"
}
