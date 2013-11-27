package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.realtime.Realtime
import org.hyperscala.html.{tag, HTMLTag}
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.property.Property
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.selector.Selector
import org.powerscala.concurrent.Time
import org.powerscala.log.Logging
import org.hyperscala.event.EventReceived
import org.powerscala.event.processor.UnitProcessor

/**
 * Bounding wraps around HTMLTags to keep track of position and dimension as represented in the client.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Bounding extends Module with StorageComponent[Bounding, HTMLTag] with Logging with Listenable {
  val name = "bounding"
  val version = Version(1)

  val modified = new UnitProcessor[BoundingEvent]("modified")

  override def dependencies = List(Realtime)

  def init() = {
    Website().register("/bounding.js", "bounding.js")
  }

  def load() = {
    val page = Webpage()
    page.body.eventReceived.on {
      case evt => if (evt.event == "bounding") {
        val id = evt.message[String]("elementId")
        page.byId[HTMLTag](id) match {
          case Some(t) => {
            val b = apply(t)
            b.set(evt, "localX", b.localX)
            b.set(evt, "localY", b.localY)
            b.set(evt, "absoluteX", b.absoluteX)
            b.set(evt, "absoluteY", b.absoluteY)
            b.set(evt, "width", b.width)
            b.set(evt, "height", b.height)
          }
          case None => warn(s"Unable to find tag for #$id.")
        }

        Intercept.Stop
      } else {
        Intercept.Continue
      }
    }
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/bounding.js")
  }

  def monitor(selector: Selector, frequency: Double) = {
    Realtime.sendJavaScript(s"window.bounding.monitor(${selector.content}, ${Time.millis(frequency)});", onlyRealtime = false)
  }

  def disable(selector: Selector) = {
    Realtime.sendJavaScript(s"window.bounding.remove(${selector.content});", onlyRealtime = false)
  }

  protected def create(t: HTMLTag) = new Bounding(t)
}

class Bounding(val tag: HTMLTag) extends Listenable {
  // TODO: create ReadableProperty that cannot be modified
  val localX = Property[Double]()
  val localY = Property[Double]()
  val absoluteX = Property[Double]()
  val absoluteY = Property[Double]()
  val width = Property[Double]()
  val height = Property[Double]()

  protected def set(evt: EventReceived, propertyName: String, property: Property[Double]) = evt.message.get[Double](propertyName) match {
    case Some(v) => {
      val oldValue = property()
      property := v
      Bounding.modified.fire(BoundingEvent(this, propertyName, property, oldValue, v))
    }
    case None => // Property not changed
  }
}

case class BoundingEvent(bounding: Bounding,
                         propertyName: String,
                         property: Property[Double],
                         oldValue: Double,
                         newValue: Double)