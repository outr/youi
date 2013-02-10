package org.hyperscala.ui.binder


import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.ui.dynamic.Binder

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputListString extends Binder[tag.Input, List[String]] {
  def bind(input: tag.Input) = {
    input.value.onChange {
      val csv = input.value().split(',').map(s => s.trim).toList
      valueProperty := csv
    }
    valueProperty.onChange {
      input.value := valueProperty().mkString(", ")
    }
    input.event.change := JavaScriptEvent()
  }
}
