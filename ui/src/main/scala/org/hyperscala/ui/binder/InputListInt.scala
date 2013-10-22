package org.hyperscala.ui.binder

import org.hyperscala.html.tag
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls
import org.hyperscala.realtime.RealtimeEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputListInt extends Binder[tag.Input, List[Int]] {
  def bind(input: tag.Input) = {
    input.value.change.on {
      case evt => {
        val csv = input.value().split(',').map(s => intOption(s.trim)).flatten.toList
        valueProperty := csv
      }
    }
    valueProperty.change.on {
      case evt => input.value := valueProperty().mkString(", ")
    }
    input.changeEvent := RealtimeEvent()
  }

  private def intOption(s: String) = try {
    Some(s.toInt)
  } catch {
    case t: Throwable => None
  }
}
