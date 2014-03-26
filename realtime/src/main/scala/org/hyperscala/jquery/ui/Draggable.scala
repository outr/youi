package org.hyperscala.jquery.ui

import org.hyperscala.jquery.jQueryComponent
import org.hyperscala.html.HTMLTag
import org.powerscala.StorageComponent
import org.hyperscala.web.Webpage
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Draggable private(val wrapped: HTMLTag) extends jQueryComponent {
  def functionName = "draggable"

  val addClasses = property[Boolean]("addClasses", true)
  val appendTo = property[String]("appendTo", "parent")
  val axis = property[String]("axis", null)
  val cancel = property[String]("cancel", "input,textarea,button,select,option")
  val connectToSortable = property[String]("connectToSortable", null)
  val containment = property[String]("containment", null)
  val cursor = property[String]("cursor", "auto")
  val cursorAt = property[String]("cursorAt", null)
  val delay = property[Long]("delay", 0L)
  val disabled = property[Boolean]("disabled", false)
  val distance = property[Int]("distance", 1)
  val grid = property[String]("grid", null)
  val handle = property[String]("handle", null)
  val helper = property[String]("helper", "original")
  val iframeFix = property[Boolean]("iframeFix", false)
  val opacity = property[Double]("opacity", 1.0)
  val refreshPositions = property[Boolean]("refreshPositions", false)
  val revert = property[String]("revert", null)
  val revertDuration = property[Long]("revertDuration", 500)
  val scope = property[String]("scope", "default")
  val scroll = property[Boolean]("scroll", true)
  val scrollSensitivity = property[Int]("scrollSensitivity", 20)
  val scrollSpeed = property[Int]("scrollSpeed", 20)
  val snap = property[Boolean]("snap", false)
  val snapMode = property[String]("snapMode", "both")
  val snapTolerance = property[Int]("snapTolerance", 20)
  val stack = property[Selector]("stack", null)
  val zIndex = property[Int]("zIndex", -1)

  def destroy() = call("destroy")
  def disable() = call("disable")
  def enable() = call("enable")

  lazy val createEvent = event("create")
  lazy val dragEvent = event("drag")
  lazy val startEvent = event("start")
  lazy val stopEvent = event("stop")
}

object Draggable extends StorageComponent[Draggable, HTMLTag] {
  override def apply(t: HTMLTag) = {
    t.connected[Webpage[_]] {
      case webpage => webpage.require(jQueryUI.LatestWithDefault)
    }
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Draggable(t)
}