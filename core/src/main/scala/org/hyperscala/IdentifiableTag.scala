package org.hyperscala

import event.EventReceived
import org.powerscala.reflect._
import org.hyperscala.event.processor.EventReceivedProcessor
import org.powerscala.event.Intercept
import org.hyperscala.io.HTMLWriter

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

  def receive(event: String, message: ResponseMessage): Unit = {
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
  def handle(event: String)(f: ResponseMessage => Unit): Unit = eventReceived.on {
    case evt => if (evt.event == event) {
      f(evt.message)
      Intercept.Stop
    } else {
      Intercept.Continue
    }
  }

  override protected def writeAttribute(writer: HTMLWriter, attribute: XMLAttribute) {
    if (attribute.name != "id" || !IdentifiableTag.ignoreIds) {   // Don't write ids if ignoreIds is true
      super.writeAttribute(writer, attribute)
    }
  }
}

object IdentifiableTag {
  private val _ignoreIds = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }

  def reId[T <: IdentifiableTag](t: T, newId: String = Unique()) = {
    t.id := newId
    t
  }

  /**
   * Writing HTML within this block will ignore ids.
   *
   * @param f the function to ignore ids within
   * @tparam T the return type
   * @return T
   */
  def ignoreIds[T](f: => T): T = {
    ignoreIdsStart()
    try {
      f
    } finally {
      ignoreIdsStop()
    }
  }

  def ignoreIdsStart() = _ignoreIds.set(true)

  def ignoreIdsStop() = _ignoreIds.set(false)

  /**
   * Are we currently ignoring ids?
   */
  def ignoreIds = _ignoreIds.get()
}

case class ResponseMessage(map: Map[String, Any]) {
  def apply[T](key: String)(implicit manifest: Manifest[T]) = convert[T](manifest, map(key))
  def get[T](key: String)(implicit manifest: Manifest[T]) = map.get(key).map(v => convert[T](manifest, v))
  def getOrElse[T](key: String, f: => T)(implicit manifest: Manifest[T]) = convert[T](manifest, map.getOrElse(key, f))

  private def convert[T](manifest: Manifest[T], value: Any) = {
    manifest.runtimeClass.convertTo[T]("value", value)
  }
}