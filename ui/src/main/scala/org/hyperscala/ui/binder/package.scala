package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object binder {
  implicit val inputString = new Binder[tag.Input, String] {
    def bind(input: tag.Input) = {
      input.value bind valueProperty
      valueProperty bind input.value
      input.event.change := JavaScriptEvent()
    }
  }

  implicit val inputInt = new Binder[tag.Input, Int] {
    def bind(input: tag.Input) = {
      input.value.onChange {
        valueProperty := input.value().collect {
          case c if (c.isDigit) => c
        }.toInt
      }
      valueProperty.onChange {
        input.value := valueProperty().toString
      }
      input.event.change := JavaScriptEvent()
    }
  }
}
