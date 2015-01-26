package org.hyperscala.html

import constraints.BodyChild
import org.hyperscala.event.ClientEvent
import org.hyperscala.event.processor.EventReceivedProcessor
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait FormField extends BodyChild {
  FormField

  def disabled: Property[Boolean]
  def value: Property[String]

  handle[ChangeClientEvent] {
    case evt => changeTo(evt.value)
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

case class ChangeClientEvent(tag: HTMLTag, value: String) extends ClientEvent

object FormField {
  EventReceivedProcessor.register[ChangeClientEvent]("change")

  private val _changingProperty = new ThreadLocal[Property[_]]()
  private val _changingValue = new ThreadLocal[Any]()
  private def clear() = {
    _changingProperty.remove()
    _changingValue.remove()
  }
  def changingProperty = _changingProperty.get()
  def changingValue = _changingValue.get()
  def ignorePropertyChange[T, R](property: Property[T], value: T)(f: => R): R = {
    _changingProperty.set(property)
    _changingValue.set(value)
    try {
      f
    } finally {
      clear()
    }
  }
}