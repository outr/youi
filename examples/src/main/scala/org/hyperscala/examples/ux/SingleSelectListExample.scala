package org.hyperscala.examples.ux

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.hyperscala.ui.module.ExternalStyle
import org.hyperscala.ux.{InputSingleSelectList, PredefinedSingleSelectList, UX}
import org.hyperscala.web._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SingleSelectListExample extends Webpage with Example {
  require(UX)
  require(ExternalStyle)
  require(Realtime)
  require(Gritter)

  val style = head.selector(Selector("#dropdown div, #inputDropdown div"))
  style.border := "1px solid black"
  style.borderRadius := 5.px
  style.backgroundColor := Color.White
  style.margin := "5px"
  style.padding := "5px"
  style.cursor := "pointer"
  val hoverStyle = head.selector(Selector("#dropdown div:hover, #inputDropdown div:hover"))
  hoverStyle.backgroundColor := Color.LightBlue

  // Button Single-Select
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

  body.contents.addAll(button, dropDownContainer)
  body.contents += new tag.Button(content = "Alternative Show Dropdown") {
    clickEvent.onRealtime {
      case evt => selectList.open()
    }
  }

  implicit def stringify = (o: Option[String]) => o.getOrElse("-- None --")
  val selectList = new PredefinedSingleSelectList[tag.Button, Option[String]](button, dropDownContainer)
  selectList.options := List(null, "One", "Two", "Three").map(Option.apply)
  selectList.selected.change.on {
    case evt => Gritter.add(this.webpage, "Selection Changed", s"Change from: ${evt.oldValue}, to: ${evt.newValue}.")
  }

  // Input Single-Select
  val input = new tag.Input(id = "input", value = "Strawberry")
  val inputDropDownContainer = new tag.Div(id = "inputDropdown") {
    style.width := 140.px
    style.backgroundColor := Color.Yellow
    style.border := "1px solid black"
    style.borderRadius := 5.px
  }

  body.contents.addAll(input, inputDropDownContainer)
  val inputSelectList = new InputSingleSelectList[Option[String]](input, inputDropDownContainer, validateOnKey = true) {
    override def filter(list: List[Option[String]]) = (if (select.value().isEmpty) {
      list
    } else {
      val (left, right) = list.flatten.partition(s => s.toLowerCase.contains(select.value().toLowerCase))
      (left ::: right).map(Option.apply)
    }).take(10)

    override def toString(value: Option[String]) = stringify(value)

    override def fromString(s: String) = Option(s)
  }
  inputSelectList.options := List(null, "Orange", "Strawberry", "Grape", "Cherry", "Raspberry", "Lemon").map(Option.apply)
  inputSelectList.selected.change.on {
    case evt => Gritter.add(this.webpage, "Input Selection Changed", s"Change from: ${evt.oldValue}, to: ${evt.newValue}.")
  }
}
