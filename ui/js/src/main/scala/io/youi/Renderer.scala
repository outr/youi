package io.youi

import io.youi.util.CanvasPool
import org.scalajs.dom._
import reactify.Var

class Renderer(val canvas: html.Canvas) {
  private lazy val context: Context = new Context(canvas, ratio())

  val ratio: Var[Double] = Var(ui.ratio)
  val width: Var[Double] = Var(ui.width)
  val height: Var[Double] = Var(ui.height)
  val visible: Var[Boolean] = Var(true)
  val drawable: Var[Drawable] = Var(Drawable.None)

  drawable.on(if (visible()) render())
  visible.attach(if (_) render())

  protected def init(): Unit = {
    ratio.and(width).and(height).on(updateSize())
    visible.attach {
      case true => canvas.style.display = "block"
      case false => canvas.style.display = "none"
    }
    updateSize()
  }

  def render(): Unit = {
    context.clear()
    drawable.draw(context)
  }

  private def updateSize(): Unit = {
    canvas.width = math.ceil(width * ratio).toInt
    canvas.height = math.ceil(height * ratio).toInt
    canvas.style.width = s"${math.ceil(width)}px"
    canvas.style.height = s"${math.ceil(height)}px"
    if (visible()) render()
  }
}

object Renderer extends Renderer(CanvasPool(1.0, 1.0)) {
  init()

  override protected def init(): Unit = {
    super.init()

    canvas.style.position = "absolute"
    canvas.style.left = "0px"
    canvas.style.top = "0px"
    visible := false
    document.body.appendChild(canvas)
  }
}