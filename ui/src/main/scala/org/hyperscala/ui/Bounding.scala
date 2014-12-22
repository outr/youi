package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.realtime.Realtime
import org.hyperscala.html._
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.property.Property
import org.hyperscala.web._
import org.hyperscala.selector.Selector
import org.powerscala.concurrent.Time
import org.powerscala.log.Logging
import org.hyperscala.event.EventReceived
import org.powerscala.event.processor.UnitProcessor
import org.hyperscala.javascript.dsl.JSFunction0
import com.outr.net.http.session.Session

/**
 * Bounding wraps around HTMLTags to keep track of position and dimension as represented in the client.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Bounding extends Module with StorageComponent[Bounding, HTMLTag] with Logging with Listenable {
  val name = "bounding"
  val version = Version(1)

  def modified[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("boundingEvents", new UnitProcessor[BoundingEvent]("modified"))

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/bounding.js", "bounding.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.body.eventReceived.on {
      case evt => if (evt.event == "bounding") {
        val id = evt.json.string("elementId")
        webpage.byId[HTMLTag](id) match {
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
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/bounding.js")
  }

  def monitor[S <: Session](webpage: Webpage[S], selector: Selector, frequency: Double = 0.5, selectorFunction: JSFunction0[Selector] = null) = {
    val sf = if (selectorFunction != null) {
      selectorFunction.toJS(1)
    } else {
      "null"
    }
    val js = s"bounding.monitor(${selector.content}, ${Time.millis(frequency)}, $sf);"
    Realtime.sendJavaScript(webpage, js, onlyRealtime = false)
  }

  def disable[S <: Session](webpage: Webpage[S], selector: Selector) = {
    Realtime.sendJavaScript(webpage, s"bounding.remove(${selector.content});", onlyRealtime = false)
  }

  protected def create(t: HTMLTag) = new Bounding(t)
}

class Bounding(val tag: HTMLTag) extends Listenable {
  // TODO: create ReadableProperty that cannot be modified
  val localX = Property[Double](default = Some(0.0))
  val localY = Property[Double](default = Some(0.0))
  val absoluteX = Property[Double](default = Some(0.0))
  val absoluteY = Property[Double](default = Some(0.0))
  val width = Property[Double](default = Some(0.0))
  val height = Property[Double](default = Some(0.0))

  def local2AbsoluteX(local: Double) = {
    val diff = absoluteX() - localX()
    local + diff
  }

  def local2AbsoluteY(local: Double) = {
    val diff = absoluteY() - localY()
    local + diff
  }

  def absolute2LocalX(absolute: Double) = {
    val diff = absoluteX() - localX()
    absolute - diff
  }

  def absolute2LocalY(absolute: Double) = {
    val diff = absoluteY() - localY()
    absolute - diff
  }

  protected def set(evt: EventReceived, propertyName: String, property: Property[Double]) = evt.json.doubleOption(propertyName) match {
    case Some(v) => {
      val oldValue = property()
      property := v.toDouble
      Bounding.modified(tag.webpage[Session]).fire(BoundingEvent(this, propertyName, property, oldValue, v.toDouble))
    }
    case None => // Property not changed
  }
}

case class BoundingEvent(bounding: Bounding,
                         propertyName: String,
                         property: Property[Double],
                         oldValue: Double,
                         newValue: Double)