package org.hyperscala

import event.EventReceived
import org.powerscala.reflect._
import org.powerscala.bus.Routing

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait IdentifiableTag extends Tag {
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
    fire(EventReceived(event, message)) match {
      case Routing.Stop => // Handled
      case _ => warn("Unhandled inbound message. Event: %s, Tag: %s, Message: %s".format(event, getClass.getName, message))
    }
  }
}

case class Message(content: String, map: Map[String, Any]) {
  def apply[T](key: String)(implicit manifest: Manifest[T]) = convert[T](manifest, map(key))
  def get[T](key: String)(implicit manifest: Manifest[T]) = map.get(key).map(v => convert[T](manifest, v))
  def getOrElse[T](key: String, f: => T)(implicit manifest: Manifest[T]) = convert[T](manifest, map.getOrElse(key, f))

  private def convert[T](manifest: Manifest[T], value: Any) = {
    manifest.erasure.convertTo[T](value)
  }
}