package org.hyperscala.fabricjs

import org.hyperscala.Container
import org.hyperscala.fabricjs.event._
import org.hyperscala.fabricjs.paint.GradientPaint
import org.hyperscala.fabricjs.prop.{CanvasProperty, ObjectProperty}
import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.javascript._
import org.hyperscala.javascript.dsl._
import org.hyperscala.web.Webpage
import org.powerscala.hierarchy.{ParentLike, MutableContainer}
import org.powerscala.hierarchy.event.Descendants
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala._

import scala.reflect.ManifestFactory

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StaticCanvas(val canvas: tag.Canvas) extends MutableContainer[Object] {
  Storage.set(canvas, "fabricjs.StaticCanvas", this)

  canvas.handle[CanvasEvent] {
    case evt => _events.find(p => p.name == evt.eventType) match {
      case Some(p) => p.asInstanceOf[CanvasEventProcessor[CanvasEvent]].fire(evt)
      case None => throw new RuntimeException(s"Unable to find processor for type: ${evt.eventType}")
    }
  }
  canvas.handle[ObjectEvent] {
    case evt => evt.obj._events.find(p => p.name == evt.eventType) match {
      case Some(p) => p.asInstanceOf[ObjectEventProcessor[ObjectEvent]].fire(evt)
      case None => throw new RuntimeException(s"Unable to find processor for type: ${evt.eventType}")
    }
  }

  implicit val childManifest = ManifestFactory.classType[Object](classOf[Object])

  val id = Unique()
  protected def className = "StaticCanvas"

  private var _properties = List.empty[CanvasProperty[_]]
  private[fabricjs] var _events = List.empty[CanvasEventProcessor[_ <: CanvasEvent]]

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

  lazy val beforeRenderEvent = new BeforeRenderEventProcessor(this)
  lazy val afterRenderEvent = new AfterRenderEventProcessor(this)
  lazy val canvasClearedEvent = new CanvasClearedEventProcessor(this)
  lazy val objectAddedEvent = new ObjectAddedEventProcessor(this)
  lazy val objectRemovedEvent = new ObjectRemovedEventProcessor(this)

  eval(JavaScriptString(s"FabricJS.init('$id', new fabric.$className('${canvas.identity}'));"))

  listen[PropertyChangeEvent[_], Unit, Unit]("change", Priority.Normal, Descendants) {
    case evt => evt.property match {
      case cp: CanvasProperty[_] => eval(s"FabricJS.set('$id', null, '${cp.name}', ${JavaScriptContent.toJS(evt.newValue)});")
      case op: ObjectProperty[_] => evt.newValue match {
        case gradient: GradientPaint => {
          eval(gradient.toJS(op.o, op.name))
          renderAll()
        }
        case value => eval(s"FabricJS.set('$id', '${op.o.id}', '${op.name}', ${op.o.toJS(op.name, value)});")
      }
      case cep: CanvasEventProperty[_] => {
        val handler =
          s"""function(options) {
             |  ${JavaScriptContent.toJS(cep.p.js())}
              |}""".stripMargin
        eval(s"FabricJS.canvasEvent('${cep.p.canvas.id}', '${cep.p.name}', $handler)")
      }
      case oep: ObjectEventProperty[_] => {
        val handler =
          s"""function(options) {
             |  ${oep.p.obj.toJS(oep.p.name, oep.p.js())}
              |}""".stripMargin
        eval(s"FabricJS.objectEvent('${oep.p.obj.id}', '${oep.p.name}', $handler);")
      }
    }
  }

  childAdded.on {
    case evt => add(evt.child.asInstanceOf[Object])
  }
  childRemoved.on {
    case evt => remove(evt.child.asInstanceOf[Object])
  }

  def renderAll() = eval(s"FabricJS.canvas['$id'].renderAll();")

  private def add(o: Object) = o.addToCanvas(this)

  private def remove(o: Object) = {
    eval(s"FabricJS.remove('$id', '${o.id}');")
  }

  protected[fabricjs] def eval(js: JavaScriptContent) = canvas.connected[Webpage] {
    case webpage => webpage.eval(js)
  }

  protected def prop[T](name: String, default: T)(implicit manifest: Manifest[T]) = synchronized {
    val p = new CanvasProperty[T](name, this)(manifest)
    p := default
    _properties = p :: _properties
    p
  }

  def byId[T <: Object](id: String)(implicit manifest: Manifest[T]) = TypeFilteredIterator[T](ParentLike.descendants(this)).find {
    case obj: Object => obj.id == id
    case _ => false
  }

  def getById[T <: Object](id: String)(implicit manifest: Manifest[T]) = {
    byId[T](id)(manifest).getOrElse(throw new NullPointerException("Unable to find '%s' by id.".format(id)))
  }

  def byType[T](implicit manifest: Manifest[T]) = TypeFilteredIterator[T](ParentLike.descendants(this)).toStream
}

object StaticCanvas {
  def apply(container: Container[BodyChild], width: Int, height: Int, id: String = Unique()) = {
    val t = new tag.Canvas(id = id, width = width, height = height)
    container.contents += t

    new StaticCanvas(t)
  }

  def get(t: tag.Canvas) = Storage.get[String, StaticCanvas](t, "fabricjs.StaticCanvas")
}