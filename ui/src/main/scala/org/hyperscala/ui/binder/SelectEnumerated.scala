package org.hyperscala.ui.binder

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.ui.Binder
import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class SelectEnumerated[E <: EnumEntry[E]](enumerated: Enumerated[E],
                                          nullAllowed: Boolean = true,
                                          nullDisplay: String = "-- Please Select an Option --")
                                         (implicit manifest: Manifest[E]) extends Binder[tag.Select, E]()(manifest) {
  def bind(select: tag.Select) = {
    select.event.change := JavaScriptEvent()
    select.contents.clear()
    if (nullAllowed) {
      select.contents += new tag.Option(value = "null", content = nullDisplay)
    }
    enumerated.values.foreach {
      case e => select.contents += new tag.Option(value = e.name(), content = e.toString())
    }

    select.value.onChange {
      val v = select.value()
      val e = if (v == "null") {
        null.asInstanceOf[E]
      } else {
        enumerated(v)
      }
      valueProperty := e
    }

    valueProperty.onChange {
      val v = valueProperty() match {
        case null => "null"
        case value => value.name()
      }
      select.value := v
    }
  }
}
