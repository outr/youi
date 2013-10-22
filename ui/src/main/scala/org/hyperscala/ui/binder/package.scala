package org.hyperscala.ui

import dynamic.Binder
import org.hyperscala.html._

import language.reflectiveCalls
import org.hyperscala.realtime.RealtimeEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object binder {
  implicit def inputString = new Binder[tag.Input, String] {
    def bind(input: tag.Input) = {
      input.value bind valueProperty
      valueProperty bind input.value

      input.changeEvent := RealtimeEvent()
    }
  }

  implicit def textAreaString = new Binder[tag.TextArea, String] {
    def bind(textArea: tag.TextArea) = {
      textArea.content bind valueProperty
      valueProperty bind textArea.content
      textArea.changeEvent := RealtimeEvent()
    }
  }

  implicit def inputInt = new Binder[tag.Input, Int] {
    def bind(input: tag.Input) = {
      input.value := valueProperty().toString
      input.value.change.on {
        case evt => {
          valueProperty := (input.value().collect {
            case c if (c.isDigit) => c
          } match {
            case "" => 0
            case s => s.toInt
          })
        }
      }
      valueProperty.change.on {
        case evt => input.value := valueProperty().toString
      }
      input.changeEvent := RealtimeEvent()
    }
  }

  implicit def selectBoolean = new SelectBoolean()

  implicit def inputListInt = new InputListInt
}
