package io.youi.component

import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.{Color, dom, ui}
import reactify.Var

class Sidebar() extends Component(dom.create.div) with PositionSupport with SizeSupport {
  val pinned: Var[Boolean] = Var(false)
  val open: Var[Boolean] = Var(false)

  position.x @= 0.0
  position.y @= 0.0
//  position.z @= 1000
//  position.`type` @= PositionType.Absolute
//  display @= Display.InlineBlock
  size.width @= 260.0
  size.height := ui.size.height
  backgroundColor @= Color.Black
}