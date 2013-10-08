package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.ui.wrapped.{Positioning, Positionable}
import org.hyperscala.css.attributes.Position
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.dsl._
import org.hyperscala.css.Style

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PositionableExample extends Example {
  Webpage().require(Positionable)
  Webpage().require(Realtime)

  val div = new tag.Div(id = "myDiv", content = "Positioned Element")
  div.style.width := 100.px
  div.style.height := 100.px
  div.style.paddingAll(5.px)
  div.style.position := Position.Absolute
  div.style.backgroundColor := Color.LightBlue
  contents += div

  val positionable = Positionable(div)
  positionable.frequency := 1.0
  val centerHorizontally = ((window.innerWidth - $(div).width()) / 2) + "px"
  val centerVertically = ((window.innerHeight - $(div).height()) / 2) + "px"
  positionable.positioning := List(Positioning(Style.left, centerHorizontally), Positioning(Style.top, centerVertically))
}