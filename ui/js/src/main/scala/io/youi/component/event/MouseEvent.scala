package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.Point
import io.youi.component.Component

class MouseEvent(val component: Component, val evt: PIXI.interaction.InteractionEvent) {
  val time: Long = System.currentTimeMillis()
  val identifier: Int = (evt.data.identifier: Any) match {
    case _: String => 0
    case i: Int => i
  }
  val globalX: Double = evt.data.global.x
  val globalY: Double = evt.data.global.y
  val (x, y) = {
    val p = evt.data.getLocalPosition(component.instance)
    p.x -> p.y
  }
  lazy val local: Point = Point(x, y)
  lazy val global: Point = Point(globalX, globalY)
  lazy val inside: Boolean = x >= 0.0 && y >= 0.0 && x <= component.size.width() && y <= component.size.height()
  def stopped: Boolean = evt.stopped
  def stopPropagation(): Unit = evt.stopPropagation()
  def preventDefault(): Unit = evt.data.originalEvent.asInstanceOf[org.scalajs.dom.MouseEvent].preventDefault()

  override def toString: String = s"MouseEvent(identifier: $identifier, x: $x, y: $y, globalX: $globalX, globalY: $globalY, inside: $inside)"
}