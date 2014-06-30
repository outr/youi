package org.hyperscala.html

import constraints.BodyChild
import org.powerscala.property.Property
import org.hyperscala.ChangeTagMessage
import argonaut._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait FormField extends BodyChild {
  def disabled: Property[Boolean]
  def value: Property[String]

  override def receive(event: String, json: JsonObject) = event match {
    case "change" => {
      val m = json.as[ChangeTagMessage]
      m.value match {
        case Some(v) => processChange(v)
        case None => // No change data sent
      }
      super.receive(event, json)
    }
    case _ => super.receive(event, json)
  }

  protected def processChange(value: Json) = value match {
    case _ if value.isString => changeTo(value.stringOrEmpty)
    case _ => throw new RuntimeException(s"Unsupported Json type: ${value.getClass} ($value) for ${getClass.getName} ($xmlLabel / $identity).")
  }

  def changeTo(newValue: String) = {
    FormField._changingProperty.set(value)
    FormField._changingValue.set(newValue)
    try {
      value := newValue
    } finally {
      FormField.clear()
    }
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