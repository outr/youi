package org.hyperscala.ui

import org.hyperscala.event.BrowserEvent
import org.hyperscala.module.Module
import org.powerscala.json.TypedSupport
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.realtime.Realtime
import org.hyperscala.html._
import org.hyperscala.javascript.dsl._
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.property.Property
import org.hyperscala.web._
import org.hyperscala.selector.Selector
import org.powerscala.concurrent.Time
import org.powerscala.log.Logging
import org.powerscala.event.processor.UnitProcessor
import org.hyperscala.javascript.dsl.JSFunction0
import com.outr.net.http.session.Session

/**
 * Bounding wraps around HTMLTags to keep track of position and dimension as represented in the client.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Bounding extends Module with StorageComponent[Bounding, HTMLTag] with Logging with Listenable {
  TypedSupport.register("bounding", classOf[BoundingEvent])

  val name = "bounding"
  val version = Version(1)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/bounding.js", "bounding.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/bounding.js")
  }

  def monitor[S <: Session](webpage: Webpage[S], selector: Selector, frequency: Double = 0.5, selectorFunction: JSFunction0[Selector] = null) = {
    val sf = if (selectorFunction != null) {
      selectorFunction.toJS(1)
    } else {
      "null"
    }
    val js = s"bounding.monitor(${selector.content}, ${Time.millis(frequency)}, $sf);"
    webpage.eval(js)
  }

  def disable[S <: Session](webpage: Webpage[S], selector: Selector) = {
    webpage.eval(s"bounding.remove(${selector.content});")
  }

  protected def create(t: HTMLTag) = new Bounding(t)
}

class Bounding(val tag: HTMLTag) extends Listenable {
  private val _localX = Property[Double](default = Some(0.0))
  private val _localY = Property[Double](default = Some(0.0))
  private val _absoluteX = Property[Double](default = Some(0.0))
  private val _absoluteY = Property[Double](default = Some(0.0))
  private val _width = Property[Double](default = Some(0.0))
  private val _height = Property[Double](default = Some(0.0))

  def localX = _localX.readOnlyView
  def localY = _localY.readOnlyView
  def absoluteX = _absoluteX.readOnlyView
  def absoluteY = _absoluteY.readOnlyView
  def width = _width.readOnlyView
  def height = _height.readOnlyView

  tag.handle[BoundingEvent] {
    case evt => {
      _localX := evt.localX
      _localY := evt.localY
      _absoluteX := evt.absoluteX
      _absoluteY := evt.absoluteY
      _width := evt.width
      _height := evt.height
    }
  }

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
}

case class BoundingEvent(tag: HTMLTag, localX: Double, localY: Double, absoluteX: Double, absoluteY: Double, width: Double, height: Double) extends BrowserEvent