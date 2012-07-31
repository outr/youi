package org.hyperscala.examples.numberguess

import org.hyperscala.html._
import attributes.{Target, InputType}
import org.hyperscala.web.HTMLPage

import org.powerscala.property._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object NumberGuess extends HTMLPage {
  title := "NumberGuess"

  /*head += JavaScript {
    val guesses = V(0, "guesses")
    val random = V(Math.floor((Math.random() * 100.0) + 1.0), "random")
    JsFunction.f0("validateGuess") {
      val message = V.content(document.getElementById[H5]("message"), "message")
      val input = V.content(document.getElementById[Input]("input"), "input")
      val guess = V(parseInt(input.value), "guess")
      message.contents.clear()
      guesses := guesses + 1
      If (isNaN(guess)) {
        message.contents += js.Var.format("Please guess a number between 1 and 100. %s is not a valid guess.", input.value)
      }
      ElseIf (guess < random) {
        message.contents += js.Var.format("Your guess of %s is too low. Try a higher number.", guess)
      }
      ElseIf (guess > random) {
        message.contents += js.Var.format("Your guess of %s is too high. Try a lower number.", guess)
      }
      Else {
        message.contents += js.Var.format("You guessed it in only %s tries!", guesses)
        input.disabled := Constant(true)
        document.getElementById[Button]("button").disabled := Constant(true)
      }
    }
    JsFunction.f0("resetGame") {
      val message = V.content(document.getElementById[H5]("message"), "message")
      message.contents.clear()
      message.contents += Constant("Guess a number between 0 and 100.")
      val input = V.content(document.getElementById[Input]("input"), "input")
      input.value := Constant("")
      input.disabled := Constant(false)
      document.getElementById[Button]("button").disabled := Constant(false)
      guesses := 0
      random := Math.floor((Math.random() * 100.0) + 1.0)
    }
  }*/

  body.style().font.family := "sans-serif"
  body.contents += new Div {
    style.width := 600.px
    style.margin := "0px auto"
    contents += new H1(content = "Number Guess Example")
    contents += new H5(id = "message", content = "Guess a number between 0 and 100.")
    contents += new P {
      contents += new Input(id = "input", inputType = InputType.Number)
      contents += new Button(id = "button", content = "Guess") {
        //        event.click := JavaScript("validateGuess();")
      }
      contents += new Button(content = "Reset") {
        //        event.click := JavaScript("resetGame();")
      }
    }
    contents += new P {
      contents += "Created by "
      contents += new A(href = "http://www.matthicks.com", content = "Matt Hicks")
    }
    contents += new A(content = "View Original Source",
      target = Target.Blank,
      href = "https://github.com/darkfrog26/webframework/blob/master/examples/numberguess/src/main/scala/com/outr/webframework/numberguess/NumberGuess.scala")
  }
}
