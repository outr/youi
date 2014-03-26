package org.hyperscala.numberguess

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html._
import org.hyperscala.html.attributes.{ButtonType, InputType}
import org.hyperscala.realtime.Realtime
import scala.util.Random
import org.hyperscala.ui.wrapped.WrappedInput
import org.hyperscala.jquery.dsl._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class NumberGuessServerPage[S <: Session](website: Website[S]) extends Webpage[S](website) {
  require(Realtime)
  Realtime.connectForm(this)

  var solution: Int = _
  var guesses: Int = _

  val message = new tag.H5(id = "message")
  val input = new tag.Input(id = "input", inputType = InputType.Number)
  val number = WrappedInput.int(input)

  val guessButton = new tag.Button(id = "button", content = "Guess", buttonType = ButtonType.Submit)
  val resetButton = new tag.Button(content = "Reset", buttonType = ButtonType.Button) {
    clickEvent.on {
      case evt => reset()
    }
  }
  val form = new tag.Form(id = "form") {
    submitEvent.on {
      case evt => guess()
    }

    contents += input
    contents += guessButton
    contents += resetButton
  }

  body.contents += new tag.H1(content = "Number Guess Example - Server Driven")
  body.contents += message
  body.contents += form

  reset()

  def guess(): Unit = {
    val g = number.property()
    guesses += 1
    if (g < 0 || g > 100) {
      setMessage("Please enter a valid number between 0 and 100.")
    } else if (g < solution) {
      setMessage(s"$g is too low, try a higher number!")
    } else if (g > solution) {
      setMessage(s"$g is too high, try a lower number!")
    } else {
      solved()
    }
    Realtime.send(this, $(input).focus())
    Realtime.send(this, $(input).select())
  }

  def reset(): Unit = {
    solution = new Random().nextInt(100)
    guesses = 0
    input.disabled := false
    input.value := null
    guessButton.disabled := false

    setMessage("Guess a number between 0 and 100.")
  }

  def solved(): Unit = {
    setMessage(s"$solution is correct! You got it in $guesses tries!")
    input.disabled := true
    guessButton.disabled := true
  }

  def setMessage(content: String): Unit = {
    message.contents.replaceWith(new tag.Span(content = content))
  }
}
