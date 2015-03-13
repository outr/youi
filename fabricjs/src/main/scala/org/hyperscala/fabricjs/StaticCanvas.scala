package org.hyperscala.fabricjs

import org.hyperscala.fabricjs.prop.{CanvasProperty, ObjectProperty}
import org.hyperscala.html.tag
import org.hyperscala.javascript._
import org.hyperscala.javascript.dsl._
import org.hyperscala.web.Webpage
import org.powerscala.hierarchy.MutableContainer
import org.powerscala.hierarchy.event.Descendants
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.{Color, Priority, Unique}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StaticCanvas(val canvas: tag.Canvas) extends MutableContainer[Object] {
  val id = Unique()
  protected def className = "StaticCanvas"

  private var _properties = List.empty[CanvasProperty[_]]
  def properties = _properties

  lazy val allowTouchScrolling = prop("allowTouchScrolling", false)
  lazy val backgroundColor = prop[Color]("backgroundColor", null)
  lazy val backgroundImage = prop[String]("backgroundImage", null)
  lazy val clipTo = prop[JavaScriptContent]("clipTo", null)
  lazy val controlsAboveOverlay = prop("controlsAboveOverlay", false)
  lazy val imageSmoothingEnabled = prop("imageSmoothingEnabled", true)
  lazy val includeDefaultValues = prop("includeDefaultValues", true)
  lazy val overlayColor = prop[Color]("overlayColor", null)
  lazy val overlayImage = prop[String]("overlayImage", null)
  lazy val preserveObjectStacking = prop("preserveObjectStacking", false)
  lazy val renderOnAddRemove = prop("renderOnAddRemove", true)
  lazy val stateful = prop("stateful", true)
  lazy val svgViewportTransformation = prop("svgViewportTransformation", true)
  lazy val viewportTransform = prop[Array[Double]]("viewportTransform", null)

  eval(JavaScriptString(s"FabricJS.canvas['$id'] = new fabric.$className('${canvas.identity}');"))

  listen[PropertyChangeEvent[_], Unit, Unit]("change", Priority.Normal, Descendants) {
    case evt => evt.property match {
      case cp: CanvasProperty[_] => eval(s"FabricJS.set('$id', null, '${cp.name}', ${JavaScriptContent.toJS(evt.newValue)});")
      case op: ObjectProperty[_] => eval(s"FabricJS.set('$id', '${op.o.id}', '${op.name}', ${JavaScriptContent.toJS(evt.newValue)});")
    }
  }

  childAdded.on {
    case evt => add(evt.child.asInstanceOf[Object])
  }
  childRemoved.on {
    case evt => remove(evt.child.asInstanceOf[Object])
  }

  private def add(o: Object) = o.addToCanvas(this)

  private def remove(o: Object) = {
    eval(s"FabricJS.remove('$id', '${o.id}');")
  }

  protected[fabricjs] def eval(js: JavaScriptContent) = canvas.connected[Webpage[_]] {
    case webpage => webpage.eval(js)
  }

  protected def prop[T](name: String, default: T)(implicit manifest: Manifest[T]) = synchronized {
    val p = new CanvasProperty[T](name, this)(manifest)
    p := default
    _properties = p :: _properties
    p
  }
}