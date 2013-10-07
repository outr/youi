package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.ui.wrapped.{Changes, Positionable}
import org.hyperscala.css.attributes.Position
import org.hyperscala.javascript.{JSFunction1, JavaScriptString}

import org.hyperscala.realtime.dsl._
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

  val centered = new JavaScriptString(
    """
      |function(changes) {
      | var div = $('#myDiv');
      | var x = (window.innerWidth / 2) - (div.width() / 2);
      | changes.style.left = x + 'px';
      |}
    """.stripMargin) with JSFunction1[Changes, Unit]

  val positionable = Positionable(div)
  positionable.frequency := 1.0
  val centerHorizontally = $(div).css(Style.left, null)
  // val centerHorizontally = $(div).css(Style.left, ((window.innerWidth / 2) - ($(div).width() / 2)) + "px")
  positionable.positioning := List(centered)
}