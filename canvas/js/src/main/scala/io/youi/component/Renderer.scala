package io.youi.component

import io.youi.{AnimationFrame, Drawable}
import org.scalajs.dom.{document, html}

class Renderer(canvas: html.Canvas) extends Container {
  override protected[youi] lazy val drawable: Drawable = new Drawable(canvas, swapCanvases = false)

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
