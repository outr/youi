package org.hyperscala.examples.ux

import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.hyperscala.ui.module.ExternalStyle
import org.hyperscala.ux.{PredefinedSingleSelectList, UX}
import org.hyperscala.web._
import org.hyperscala.examples.Example
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SingleSelectListExample extends Example {
  this.require(UX)
  this.require(ExternalStyle)
  this.require(Realtime)
  this.require(Gritter)

  connected[Webpage[_]] {
    case webpage => {
      val style = webpage.head.selector(Selector("#dropdown div"))
      style.border := "1px solid black"
      style.borderRadius := 5.px
      style.backgroundColor := Color.White
      style.margin := "5px"
      style.padding := "5px"
      style.cursor := "pointer"
      val hoverStyle = webpage.head.selector(Selector("#dropdown div:hover"))
      hoverStyle.backgroundColor := Color.LightBlue
    }
  }

  val button = new tag.Button(id = "button", content = "Show Dropdown")
  val dropDownContainer = new tag.Div(id = "dropdown") {
    style.width := 140.px
    style.backgroundColor := Color.Yellow
    style.border := "1px solid black"
    style.borderRadius := 5.px

    (0 until 10).foreach {
      case index => contents += new tag.Div(content = s"Entry #${index + 1}") {
        clickEvent.onRealtime {
          case evt => info(s"Clicked #$index")
        }
      }
    }
  }

  contents.addAll(button, dropDownContainer)
  contents += new tag.Button(content = "Alternative Show Dropdown") {
    clickEvent.onRealtime {
      case evt => selectList.open()
    }
  }

  implicit def stringify = (o: Option[String]) => o.getOrElse("-- None --")
  val selectList = new PredefinedSingleSelectList[Option[String]](button, dropDownContainer)
  selectList.options := List(null, "One", "Two", "Three").map(Option.apply)
  selectList.selected.change.on {
    case evt => Gritter.add(this.webpage, "Selection Changed", s"Change from: ${evt.oldValue}, to: ${evt.newValue}.")
  }
}
