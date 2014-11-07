package org.hyperscala.jquery.ui

import argonaut.Argonaut._
import argonaut.CodecJson
import org.hyperscala.TagMessage
import org.hyperscala.event.EventReceived
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.{JSMapper, jQueryComponent}
import org.hyperscala.selector.Selector
import org.hyperscala.web._
import org.powerscala.StorageComponent


/**
 * @author Matt Hicks <matt@outr.com>
 */
class Droppable private(val wrapped: HTMLTag, val autoInit: Boolean = true) extends jQueryComponent {
  def functionName = "droppable"

  val accept = property[Selector]("accept", Selector.all)
  val activeClass = property[String]("activeClass", null)
  val addClasses = property[Boolean]("addClasses", true)
  val disabled = property[Boolean]("disabled", false)
  val greedy = property[Boolean]("greedy", false)
  val hoverClass = property[String]("hoverClass", null)
  val scope = property[String]("scope", "default")
  val tolerance = property[String]("tolerance", "intersect")

  def destroy() = call("destroy")
  def disable() = call("disable")
  def enable() = call("enable")

  lazy val activateEvent = event("activate")
  lazy val createEvent = event("create")
  lazy val deactivateEvent = event("deactivate")
  lazy val dropEvent = event[DropEvent]("drop", mapper)
  lazy val out = event("out")
  lazy val over = event("over")

  private val converter = (evt: EventReceived) => {
    val m = evt.json.as[DropTagMessage]
    val draggable = wrapped.webpage.body.byId[HTMLTag](m.draggable) match {
      case Some(tag) => Draggable(tag)
      case None => null
    }
    val helper = wrapped.webpage.body.byId[HTMLTag](m.helper).orNull
    val draggableLeft = m.draggableLeft
    val draggableTop = m.draggableTop
    val helperLeft = m.helperLeft
    val helperTop = m.helperTop
    DropEvent(draggable, helper, draggableLeft, draggableTop, helperLeft, helperTop)
  }
  private val mapper = JSMapper(List("event", "ui"), DropEvent.variables2JSON, converter)
}

object Droppable extends StorageComponent[Droppable, HTMLTag] {
  override def apply(t: HTMLTag) = {
    t.require(jQueryUI)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Droppable(t)
}

case class DropEvent(draggable: Draggable,
                     helper: HTMLTag,
                     positionLeft: Double,
                     positionTop: Double,
                     offsetLeft: Double,
                     offsetTop: Double)

object DropEvent {
  val variables2JSON = new JavaScriptString("{ draggable: ui.draggable.attr('id'), helper: ui.helper.attr('id'), draggableLeft: ui.position.left, draggableTop: ui.position.top, helperLeft: ui.offset.left, helperTop: ui.offset.top }")
}

case class DropTagMessage(id: String, draggable: String, helper: String, draggableLeft: Double, draggableTop: Double, helperLeft: Double, helperTop: Double) extends TagMessage

object DropTagMessage {
  implicit def DropTagMessageCodecJson: CodecJson[DropTagMessage] = casecodec7(DropTagMessage.apply, DropTagMessage.unapply)("id", "draggable", "helper", "draggableLeft", "draggableTop", "helperLeft", "helperTop")
}