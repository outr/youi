package org.hyperscala

import event.EventReceived
import org.powerscala.reflect._
import org.hyperscala.event.processor.EventReceivedProcessor
import org.powerscala.event.Intercept

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait IdentifiableTag extends Tag {
  val eventReceived = new EventReceivedProcessor()

  val id = PropertyAttribute[String]("id", null)

  /**
   * Gets the id value and sets it to a unique value if it's null.
   */
  def identity = id() match {
    case null => {
      val uuid = Unique()
      id := uuid
      uuid
    }
    case value => value
  }

  def receive(event: String, message: Message): Unit = {
    eventReceived.fire(EventReceived(event, message)) match {
      case Intercept.Stop => // Handled
      case _ => warn("IdentifiableTag.receive: Unhandled inbound message. Event: %s, Tag: %s (%s), Message: %s".format(event, getClass.getName, xmlLabel, message))
    }
  }

  /**
   * Handles received events by name.
   *
   * @param event the name of the event being received
   * @param f the function to receive the message
   */
  def handle(event: String)(f: Message => Unit): Unit = eventReceived.on {
    case evt => if (evt.event == event) {
      f(evt.message)
      Intercept.Stop
    } else {
      Intercept.Continue
    }
  }
}

object IdentifiableTag {
  def reId[T <: IdentifiableTag](t: T, newId: String = Unique()) = {
    t.id := newId
    t
  }
}

case class Message(content: String, map: Map[String, Any]) {
  def apply[T](key: String)(implicit manifest: Manifest[T]) = convert[T](manifest, map(key))
  def get[T](key: String)(implicit manifest: Manifest[T]) = map.get(key).map(v => convert[T](manifest, v))
  def getOrElse[T](key: String, f: => T)(implicit manifest: Manifest[T]) = convert[T](manifest, map.getOrElse(key, f))

  private def convert[T](manifest: Manifest[T], value: Any) = {
    manifest.runtimeClass.convertTo[T]("value", value)
  }
}