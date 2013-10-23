package org.hyperscala.jquery.ui

import org.hyperscala.html.HTMLTag
import org.hyperscala.jquery.{JSMapper, jQueryComponent}
import org.hyperscala.selector.Selector
import org.powerscala.StorageComponent
import org.hyperscala.web.Webpage
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.event.EventReceived

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Droppable private(val wrapped: HTMLTag) extends jQueryComponent {
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
  lazy val dropEvent = event[DropEvent]("drop", DropEvent.Mapper)
  lazy val out = event("out")
  lazy val over = event("over")
}

object Droppable extends StorageComponent[Droppable, HTMLTag] {
  override def apply(t: HTMLTag) = {
    Webpage().require(jQueryUI.LatestWithDefault)
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
  private val variables2JSON = new JavaScriptString("{ draggable: ui.draggable.attr('id'), helper: ui.helper.attr('id'), position: ui.position, offset: ui.offset }")
  private val converter = (evt: EventReceived) => {
    val draggable = Webpage().body.byId[HTMLTag](evt.message[String]("draggable")) match {
      case Some(tag) => Draggable(tag)
      case None => null
    }
    val helper = Webpage().body.byId[HTMLTag](evt.message[String]("helper")).getOrElse(null)
    val position = evt.message[Map[String, Double]]("position")
    val offset = evt.message[Map[String, Double]]("offset")
    val draggableLeft = position("left")
    val draggableTop = position("top")
    val helperLeft = offset("left")
    val helperTop = offset("top")
    DropEvent(draggable, helper, draggableLeft, draggableTop, helperLeft, helperTop)
  }
  val Mapper = JSMapper(List("event", "ui"), variables2JSON, converter)
}