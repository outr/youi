package org.hyperscala.examples.ui


import org.hyperscala.css.Style
import org.hyperscala.css.attributes.Position
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime._
import org.hyperscala.ui.wrapped.{Changeable, Changes, Changing}
import org.hyperscala.web._
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ChangeableExample extends Webpage with Example {
  import ChangeableExample._

  require(Changeable)
  require(Realtime)

  this.connectForm()

  body.contents += new tag.P {
    contents += "Changeable module allows the use of a JavaScript DSL to define style changes to be applied a tag."
  }

  val div = new tag.Div(id = "myDiv", content = "Positioned Element")
  div.style.width := 100.px
  div.style.height := 100.px
  div.style.paddingAll(5.px)
  div.style.position := Position.Absolute
  div.style.backgroundColor := Color.LightBlue
  body.contents += div

  var horizontal: JSFunction1[Changes, Unit] = HorizontalCenter
  var vertical: JSFunction1[Changes, Unit] = VerticalMiddle

  val changeable = Changeable(div)
  changeable.frequency := 0.1
  updatePositioning()

  body.contents += new tag.Div {
    contents += new tag.Button(content = "Left") {
      clickEvent.on {
        case evt => updatePositioning(x = HorizontalLeft)
      }
    }
    contents += new tag.Button(content = "Center") {
      clickEvent.on {
        case evt => updatePositioning(x = HorizontalCenter)
      }
    }
    contents += new tag.Button(content = "Right") {
      clickEvent.on {
        case evt => updatePositioning(x = HorizontalRight)
      }
    }
  }

  body.contents += new tag.Div {
    contents += new tag.Button(content = "Top") {
      clickEvent.on {
        case evt => updatePositioning(y = VerticalTop)
      }
    }
    contents += new tag.Button(content = "Middle") {
      clickEvent.on {
        case evt => updatePositioning(y = VerticalMiddle)
      }
    }
    contents += new tag.Button(content = "Bottom") {
      clickEvent.on {
        case evt => updatePositioning(y = VerticalBottom)
      }
    }
  }

  body.contents += new tag.Div {
    contents += new tag.Button(content = "Under Logo") {
      clickEvent.on {
        case evt => updatePositioning(x = HorizontalLeftLogo, y = VerticalUnderLogo)
      }
    }
  }

  def updatePositioning(x: JSFunction1[Changes, Unit] = null, y: JSFunction1[Changes, Unit] = null) = {
    if (x != null) {
      horizontal = x
    }
    if (y != null) {
      vertical = y
    }
    changeable.changing := List(horizontal, vertical)
  }
}

object ChangeableExample {
  val myDiv = $("#myDiv")
  val logo = $("#logo")

  val HorizontalLeft = Changing(Style.left, 0.px)
  val HorizontalCenter = Changing(Style.left, (window.innerWidth - myDiv.width()) / 2)
  val HorizontalRight = Changing(Style.left, window.innerWidth - myDiv.width())

  val VerticalTop = Changing(Style.top, 0.px)
  val VerticalMiddle = Changing(Style.top, (window.innerHeight - myDiv.height()) / 2)
  val VerticalBottom = Changing(Style.top, window.innerHeight - myDiv.height())

  val VerticalUnderLogo = Changing(Style.top, logo.offset().top + logo.height())
  val HorizontalLeftLogo = Changing(Style.left, logo.offset().left)
}