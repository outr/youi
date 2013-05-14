package org.hyperscala.ui

import dynamic.Binder
import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent

import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object binder {
  implicit def inputString = new Binder[tag.Input, String] {
    def bind(input: tag.Input) = {
      input.value bind valueProperty
      valueProperty bind input.value

      input.changeEvent := JavaScriptEvent()
    }
  }

  implicit def textAreaString = new Binder[tag.TextArea, String] {
    def bind(textArea: tag.TextArea) = {
      textArea.content bind valueProperty
      valueProperty bind textArea.content
      textArea.changeEvent := JavaScriptEvent()
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
      input.changeEvent := JavaScriptEvent()
    }
  }

  implicit def selectBoolean = new SelectBoolean()

  implicit def inputListInt = new InputListInt
}
