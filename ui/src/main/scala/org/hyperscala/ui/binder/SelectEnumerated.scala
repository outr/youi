package org.hyperscala.ui.binder

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html._
import org.hyperscala.ui.dynamic.Binder
import language.reflectiveCalls
import org.hyperscala.realtime.RealtimeEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class SelectEnumerated[E <: EnumEntry](enumerated: Enumerated[E],
                                          nullAllowed: Boolean = true,
                                          nullDisplay: String = "-- Please Select an Option --")
                                         (implicit manifest: Manifest[E]) extends Binder[tag.Select, E]()(manifest) {
  def bind(select: tag.Select) = {
    select.changeEvent := RealtimeEvent()
    select.contents.clear()
    if (nullAllowed) {
      select.contents += new tag.Option(value = "null", content = nullDisplay)
    }
    enumerated.values.foreach {
      case e => select.contents += new tag.Option(value = e.name, content = e.toString())
    }

    select.value.change.on {
      case evt => {
        val v = select.value()
        val e = if (v == "null") {
          null.asInstanceOf[E]
        } else {
          enumerated(v)
        }
        valueProperty := e
      }
    }

    valueProperty.change.on {
      case evt => {
        val v = valueProperty() match {
          case null => "null"
          case value => value.name
        }
        select.value := v
      }
    }
  }
}
