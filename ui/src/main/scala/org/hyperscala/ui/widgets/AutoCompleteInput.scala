package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.Unique

import org.powerscala.property._
import org.powerscala.Color
import org.hyperscala.web.live._
import org.hyperscala.css.attributes.{Display, Position}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class AutoCompleteInput[T](id: String = Unique(), default: T) extends tag.Div(id = id) {
  val property = Property[T]("property", default)
  property.onChange {
    updateInput()
  }

  def updateInput() = {
    input.value := resultToString(property())
  }

  style.width := 200.px

  val input = new tag.Input(id = "%sInput".format(id)) {
    style.width := 100.pct
    style.height := 100.pct

    event.keyUp := LiveEvent(fireChange = true, preventDefault = true, onlyLast = true)

    listeners.synchronous {
      case evt: KeyUpEvent if (evt.key == Key.Escape) => completion.style.display := Display.None
      case evt: KeyUpEvent => {
        val query = value().toLowerCase
        val results = complete(query).map(r => result(r, query))
        if (results.nonEmpty) {
          completion.contents.replaceWith(results: _*)
          completion.style.display := Display.Block
        } else {
          completion.style.display := Display.None
        }
      }
    }
  }

  updateInput()

  val completion = new tag.Div(id = "%sCompletion".format(id)) {
    style.display := Display.None
    style.position := Position.Absolute
    style.background.color := Color.White
    style.border.color := Color.LightGray
    style.border.style := "solid"
    style.border.width := 1.px
  }

  contents += input
  contents += completion

  def complete(query: String): Seq[T]

  def resultToString(result: T) = result.toString

  def result(r: T, query: String) = new tag.Div with Result[T] {
    val result = r
    val resultString = resultToString(r)

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

    style.padding.left := 15.px
    style.padding.right := 15.px
    style.padding.top := 5.px
    style.padding.bottom := 5.px

    event.mouseOver := LiveEvent()
    event.mouseOut := LiveEvent()

    listeners.synchronous {
      case evt: MouseOverEvent => style.background.color := Color.LightGray
      case evt: MouseOutEvent => style.background.color := Color.White
    }
  }
}

trait Result[T] {
  def result: T
}