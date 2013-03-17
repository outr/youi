package org.hyperscala.examples.numberguess

import org.hyperscala.html._
import attributes.InputType

import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class NumberGuess extends Example {
  style.width = 600.px
  style.margin = "0px auto"
  contents += new tag.H1(content = "Number Guess Example")
  contents += new tag.H5(id = "message", content = "Guess a number between 0 and 100.")
  contents += new tag.P {
    contents += new tag.Input(id = "input", inputType = InputType.Number)
    contents += new tag.Button(id = "button", content = "Guess") {
      //        event.click := JavaScript("validateGuess();")
    }
    contents += new tag.Button(content = "Reset") {
      //        event.click := JavaScript("resetGame();")
    }
  }
  contents += new tag.P {
    contents += "Created by "
    contents += new tag.A(href = "http://www.matthicks.com", content = "Matt Hicks")
  }
}
