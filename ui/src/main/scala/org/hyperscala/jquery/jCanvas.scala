package org.hyperscala.jquery

import org.hyperscala.module.{InterfaceWithDefault, Module}
import org.powerscala.{Color, Version}
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.jquery.dsl.jQuerySelector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jCanvas(selector: jQuerySelector) {
  Webpage().require(jCanvas)

  def drawArc(properties: ArcProperty*) = {
    selector.call("drawArc", properties2Map(properties))
  }

  private def properties2Map(properties: Seq[CanvasProperty]) = {
    properties.map(cp => propertyName(cp) -> cp.value).toMap
  }

  private def propertyName(property: CanvasProperty) = {
    val name = property.getClass.getSimpleName
    name.charAt(0).toLower + name.substring(1)
  }
}

trait CanvasProperty {
  def value: Any
}

trait ArcProperty extends CanvasProperty

case class StrokeStyle(value: Color) extends ArcProperty
case class StrokeWidth(value: Int) extends ArcProperty
case class X(value: Int) extends ArcProperty
case class Y(value: Int) extends ArcProperty
case class Radius(value: Int) extends ArcProperty
case class Start(value: Int) extends ArcProperty
case class End(value: Int) extends ArcProperty

object jCanvas extends Module {
  val name = "jcanvas"
  val version = Version(2013, 11, 21)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest), Realtime)

  def init() = {
    Website().register("/js/jcanvas.min.js", "jcanvas.min.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jcanvas.min.js")
  }
}
