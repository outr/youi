package org.hyperscala.html

import constraints.BodyChild
import org.hyperscala.ResponseMessage
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait FormField extends BodyChild {
  def disabled: Property[Boolean]
  def value: Property[String]

  override def receive(event: String, message: ResponseMessage) = event match {
    case "change" => {
      if (message.map.contains("value")) {
        val v = message[String]("value")
        FormField._changingProperty.set(value)
        FormField._changingValue.set(v)
        try {
          value := v
        } finally {
          FormField.clear()
        }
      }
      super.receive(event, message)
    }
    case _ => super.receive(event, message)
  }
}

object FormField {
  private val _changingProperty = new ThreadLocal[Property[String]]()
  private val _changingValue = new ThreadLocal[String]()
  private def clear() = {
    _changingProperty.remove()
    _changingValue.remove()
  }
  def changingProperty = _changingProperty.get()
  def changingValue = _changingValue.get()
}