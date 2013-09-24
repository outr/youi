package org.hyperscala.ui.wrapped

import org.hyperscala.module.Module
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.html.{tag, HTMLTag}
import org.powerscala.event.Listenable
import org.hyperscala.jquery.jQuery
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.site.{Webpage, Website}

/**
 * Positionable wraps existing elements to allow them to position an element relative to an
 * element, alignment, or more advanced positioning scenario.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Positionable extends Module with StorageComponent[Positionable, HTMLTag] {
  val name = "positionable"
  val version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime)

  def init() = {
    Website().register("/js/positionable.js", "positionable.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/positionable.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Positionable(t)
}

class Positionable private(val wrapped: HTMLTag) extends Listenable {
  wrapped.clazz += "positionable"

//  val x = wrapped.dataWrapper[Int]("positionable-x", 0)
//  val y = wrapped.dataWrapper[Int]("positionable-y", 0)
  // TODO: introduce Updatable Module to allow adding and removing JS functions to be called periodically on an element
  // TODO: introduce Batching Module to send updates to server in batches delayed by a few milliseconds and allow overlapping
}