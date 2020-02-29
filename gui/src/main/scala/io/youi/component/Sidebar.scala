package io.youi.component

import io.youi.component.support.{OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, Overflow, PositionType}
import io.youi.task._
import io.youi.{Color, dom, ui}
import reactify.Var

class Sidebar(width: Double = 260.0) extends Container() with PositionSupport with SizeSupport with OverflowSupport {
  val adjustSpeed: Var[Double] = Var(1600.0)
  val pinned: Var[Boolean] = Var(false)
  val open: Var[Boolean] = Var(true)

  val container: Component with SizeSupport = new Component(dom.create.div) with SizeSupport {
    size.width @= width
    size.height @= ui.size.height

    element.appendChild(dom.create.text("Hello, World! Hello, World! Hello, World! Hello, World! Hello, World! Hello, World!"))
  }

  position.x @= 0.0
  position.y @= 0.0
  position.z @= 1000
  position.`type` @= PositionType.Absolute
  size.width @= width
  size.height := ui.size.height
  overflow.x @= Overflow.Hidden
  backgroundColor @= Color.Green
  element.style.border = "2px solid blue"

  children += container

  open.attachAndFire {
    case true => show()
    case false => hide()
  }

  private def show(): TaskInstance = {
    size.width.to(width).by(adjustSpeed).start()
  }

  private def hide(): TaskInstance = {
    size.width.to(0.0).by(adjustSpeed).start()
  }
}