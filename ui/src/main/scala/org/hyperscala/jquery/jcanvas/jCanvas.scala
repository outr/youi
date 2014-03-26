package org.hyperscala.jquery.jcanvas

import org.hyperscala.module.{InterfaceWithDefault, Module}
import org.powerscala.Version
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.jquery.jQuery
import org.hyperscala.javascript.dsl.Statement
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jCanvas(selector: jQuerySelector) {
  def drawArc(strokeStyle: StrokeStyle = null,
              strokeWidth: StrokeWidth = null,
              x: X = null,
              y: Y = null,
              radius: Radius = null,
              start: Start = null,
              end: End = null): Statement[Unit] = {
    arc(strokeStyle, strokeWidth, x, y, radius, start, end)
  }

  def arc(properties: ArcProperty*): Statement[Unit] = selector.call("drawArc", properties2Map(properties: _*))

  private def properties2Map(properties: CanvasProperty*) = {
    properties.collect {
      case cp if cp != null => propertyName(cp) -> cp.value
    }.toMap
  }

  private def propertyName(property: CanvasProperty) = {
    val name = property.getClass.getSimpleName
    name.charAt(0).toLower + name.substring(1)
  }
}

object jCanvas extends Module {
  val name = "jcanvas"
  val version = Version(2013, 11, 21)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest), Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/jcanvas.min.js", "jcanvas.min.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jcanvas.min.js")
  }
}
