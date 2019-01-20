package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import io.youi.drawable.Context
import io.youi.theme.{CanvasComponentTheme, Theme}
import org.scalajs.dom._

abstract class CanvasComponent(protected val element: html.Canvas,
                               val existing: Boolean = false) extends HTMLComponent[html.Canvas] with CanvasComponentTheme {
  protected lazy val context: Context = new Context(element, ratio())

  def this() = {
    this(dom.create[html.Canvas]("canvas"))
  }

  protected def draw(context: Context): Unit

  override protected def defaultParentTheme: Theme = CanvasComponent

  override def componentType: String = "CanvasComponent"

  override protected def init(): Unit = {
    super.init()

    invalidateRendering()
  }

  override protected def updateRendering(): Unit = {
    super.updateRendering()

    context.reset()
    element.width = math.ceil(size.width * ratio).toInt
    element.height = math.ceil(size.height * ratio).toInt
    draw(context)
  }
}

object CanvasComponent extends CanvasComponentTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent
}