package org.hyperscala.html

import constraints.BodyChild
import org.hyperscala.Message
import org.powerscala.property.Property

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait FormField extends BodyChild {
  def disabled: Property[Boolean]
  def value: Property[String]

  override def receive(event: String, message: Message) = event match {
    case "change" => {
      val v = message[String]("value")
      FormField._changingProperty.set(value)
      FormField._changingValue.set(v)
      try {
        value := v
      } finally {
        FormField.clear()
      }
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