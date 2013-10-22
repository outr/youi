package org.hyperscala.ui.binder


import org.hyperscala.html._
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls
import org.hyperscala.realtime.RealtimeEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputListString extends Binder[tag.Input, List[String]] {
  def bind(input: tag.Input) = {
    input.value.change.on {
      case evt => {
        val csv = input.value().split(',').map(s => s.trim).toList
        valueProperty := csv
      }
    }
    valueProperty.change.on {
      case evt => input.value := valueProperty().mkString(", ")
    }
    input.changeEvent := RealtimeEvent()
  }
}
