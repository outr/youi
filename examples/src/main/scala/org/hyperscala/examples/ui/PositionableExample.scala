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
  Realtime.connectForm()

  val div = new tag.Div(id = "myDiv", content = "Positioned Element")
  div.style.width := 100.px
  div.style.height := 100.px
  div.style.paddingAll(5.px)
  div.style.position := Position.Absolute
  div.style.backgroundColor := Color.LightBlue
  contents += div

  var horizontal: Positioning = PositionableExample.HorizontalCenter
  var vertical: Positioning = PositionableExample.VerticalMiddle

  val positionable = Positionable(div)
  positionable.frequency := 0.1
  updatePositioning()

  contents += new tag.Div {
    contents += new tag.Button(content = "Left") {
      clickEvent.on {
        case evt => updatePositioning(x = PositionableExample.HorizontalLeft)
      }
    }
    contents += new tag.Button(content = "Center") {
      clickEvent.on {
        case evt => updatePositioning(x = PositionableExample.HorizontalCenter)
      }
    }
    contents += new tag.Button(content = "Right") {
      clickEvent.on {
        case evt => updatePositioning(x = PositionableExample.HorizontalRight)
      }
    }
  }

  contents += new tag.Div {
    contents += new tag.Button(content = "Top") {
      clickEvent.on {
        case evt => updatePositioning(y = PositionableExample.VerticalTop)
      }
    }
    contents += new tag.Button(content = "Middle") {
      clickEvent.on {
        case evt => updatePositioning(y = PositionableExample.VerticalMiddle)
      }
    }
    contents += new tag.Button(content = "Bottom") {
      clickEvent.on {
        case evt => updatePositioning(y = PositionableExample.VerticalBottom)
      }
    }
  }

  contents += new tag.Div {
    contents += new tag.Button(content = "Under Logo") {
      clickEvent.on {
        case evt => updatePositioning(x = PositionableExample.HorizontalLeftLogo, y = PositionableExample.VerticalUnderLogo)
      }
    }
  }

  def updatePositioning(x: Positioning = null, y: Positioning = null) = {
    if (x != null) {
      horizontal = x
    }
    if (y != null) {
      vertical = y
    }
    positionable.positioning := List(horizontal, vertical)
  }
}

object PositionableExample {
  val myDiv = $("#myDiv")
  val logo = $("#logo")

  val HorizontalLeft = Positioning(Style.left, 0.px)
  val HorizontalCenter = Positioning(Style.left, ((window.innerWidth - myDiv.width()) / 2) + "px")
  val HorizontalRight = Positioning(Style.left, (window.innerWidth - myDiv.width()) + "px")

  val VerticalTop = Positioning(Style.top, 0.px)
  val VerticalMiddle = Positioning(Style.top, ((window.innerHeight - myDiv.height()) / 2) + "px")
  val VerticalBottom = Positioning(Style.top, (window.innerHeight - myDiv.height()) + "px")

  val VerticalUnderLogo = Positioning(Style.top, logo.offset().top + logo.height())
  val HorizontalLeftLogo = Positioning(Style.left, logo.offset().left)
}