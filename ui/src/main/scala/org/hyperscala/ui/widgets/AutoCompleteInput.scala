package org.hyperscala.ui.widgets

import org.hyperscala.css.attributes.{Display, LineStyle, Position}
import org.hyperscala.event._
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.powerscala.property._
import org.powerscala.{Color, Unique}

import scala.language.reflectiveCalls
import scala.math._

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class AutoCompleteInput[T](id: String = Unique(), default: T)(implicit manifest: Manifest[T]) extends tag.Div(id = id) with FormField {
  val property = Property[T](default = Some(default))(this, manifest)
  property.change.on {
    case evt => {
      updateInput()
      hideCompletion()
    }
  }

  def value = input.value

  def selected = if (showingCompletion) {
    completion.contents.find {
      case result: Result[_] => result.active
      case _ => false
    }.asInstanceOf[Option[Result[T]]]
  } else {
    None
  }

  def selectedIndex = if (showingCompletion) {
    selected match {
      case Some(s) => completion.contents.indexOf(s)
      case None => -1
    }
  } else {
    -1
  }

  def selectedIndex_=(index: Int) = {
    completion.contents.zipWithIndex.foreach {
      case (r, i) => {
        val result = r.asInstanceOf[Result[T]]
        result.state(i == index)
      }
    }
    selectedIndex != -1
  }

  def selectPrevious() = {
    if (selectedIndex = selectedIndex - 1) {
      // Properly went backwards
    } else {
      selectFirst()
    }
  }

  def selectNext() = {
    if (selectedIndex = selectedIndex + 1) {
      // Properly went forward
    } else {
      selectLast()
    }
  }

  def selectFirst() = {
    selectedIndex = 0
  }

  def selectLast() = {
    selectedIndex = completion.contents.length - 1
  }

  def applySelected(): Unit = if (showingCompletion) {
    selected match {
      case Some(result) if result.result != property() => property := result.result
      case _ => {
        updateInput()
        hideCompletion()
      }
    }
  } else {
    property := null.asInstanceOf[T]
  }

  def updateInput(): Unit = if (property() != null) {
    input.value := resultToString(property())
  }

  style.width := 200.px

  val input = new tag.Input(id = "%sInput".format(id)) {
    style.width := 100.pct
    style.height := 100.pct

    keyUpEvent := RealtimeEvent(fireChange = true, preventDefault = true)
    blurEvent := RealtimeEvent(preventDefault = false, delay = 100)

    blurEvent.on {
      case evt => {
        updateInput()
        hideCompletion()
      }
    }
    keyUpEvent.on {
      case evt if evt.key == Key.Return => applySelected()
      case evt if evt.key == Key.Up => selectPrevious()
      case evt if evt.key == Key.Down => selectNext()
      case evt if evt.key == Key.Escape => hideCompletion()
      case evt if evt.key == Key.Left ||
                  evt.key == Key.Right ||
                  evt.key == Key.Home ||
                  evt.key == Key.End => // Ignore
      case evt => showCompletion()
    }
  }

  updateInput()

  def disabled = input.disabled

  val completion = new tag.Div(id = "%sCompletion".format(id)) {
    style.display := Display.None
    style.position := Position.Absolute
    style.backgroundColor := Color.White
    style.borderColor := Color.LightGray
    style.borderStyle := LineStyle.Solid
    style.borderWidth := 1.px
  }

  contents += input
  contents += completion

  def complete(query: String): Seq[T]

  def resultToString(result: T) = result.toString

  def result(r: T, query: String) = new BasicResult[T](r, query, this)

  def showingCompletion = completion.style.display() != Display.None

  def showCompletion(): Unit = {
    val index = max(selectedIndex, 0)
    val query = input.value().toLowerCase
    val results = complete(query).map(r => result(r, query))
    if (results.nonEmpty) {
      completion.contents.replaceWith(results: _*)
      completion.style.display := Display.Block
      if (!(selectedIndex = index)) {
        selectLast()
      }
    } else {
      completion.style.display := Display.None
    }
  }

  def hideCompletion() = {
    completion.style.display := Display.None
  }
}

trait Result[T] {
  def result: T

  private var _active: Boolean = false

  def active = _active

  def state(active: Boolean) = {
    _active = active
  }
}

class BasicResult[T](val result: T, query: String, input: AutoCompleteInput[T]) extends tag.Div with Result[T] {
  val resultString = input.resultToString(result)

  if (query.nonEmpty) {
    val index = resultString.toLowerCase.indexOf(query)
    if (index > 0) {
      contents += resultString.substring(0, index)
    }
    contents += new tag.B {
      contents += resultString.substring(index, index + query.length)
    }
    if (index != resultString.length - 1) {
      contents += resultString.substring(index + query.length)
    }
  } else {
    contents += resultString
  }

  style.paddingLeft := 15.px
  style.paddingRight := 15.px
  style.paddingTop := 5.px
  style.paddingBottom := 5.px
  style.cursor := "pointer"

  mouseOverEvent := RealtimeEvent()
  mouseOutEvent := RealtimeEvent()
  clickEvent := RealtimeEvent()

  mouseOverEvent.on {
    case evt => {
      input.completion.contents.foreach {
        case child => child.asInstanceOf[Result[T]].state(active = false)
      }
      state(active = true)
    }
  }
  mouseOutEvent.on {
    case evt => state(active = false)
  }
  clickEvent.on {
    case evt => input.property := result
  }

  override def state(active: Boolean) = {
    super.state(active)
    active match {
      case true => style.backgroundColor := Color.LightGray
      case false => style.backgroundColor := Color.White
    }
  }
}