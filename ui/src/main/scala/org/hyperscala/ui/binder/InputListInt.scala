package org.hyperscala.ui.binder

import org.hyperscala.html.tag
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class InputListInt extends Binder[tag.Input, List[Int]] {
  def bind(input: tag.Input) = {
    input.value.onChange {
      val csv = input.value().split(',').map(s => intOption(s.trim)).flatten.toList
      valueProperty := csv
    }
    valueProperty.onChange {
      input.value := valueProperty().mkString(", ")
    }
    input.event.change := JavaScriptEvent()
  }

  private def intOption(s: String) = try {
    Some(s.toInt)
  } catch {
    case t: Throwable => None
  }
}
