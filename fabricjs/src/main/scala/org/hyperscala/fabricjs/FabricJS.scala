package org.hyperscala.fabricjs

import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import com.outr.net.http.session.Session
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.powerscala.{Color, Unique, Version}
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object FabricJS extends Module {
  val name = "fabric.js"
  val version = Version(1, 4, 0)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/hyperscala-fabric.js", "hyperscala-fabric.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.4.0/fabric.min.js")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-fabric.js")
  }
}

class StaticCanvas(canvas: tag.Canvas) {
  val id = Unique()
  protected def className = "StaticCanvas"

  eval(JavaScriptString(s"FabricJS.canvas['$id'] = new fabric.$className('${canvas.identity}');"))

  def add(o: Object) = {
    val props = o.properties.map(p => p.get.map(v => p.name -> v)).flatten.map(v => s"${v._1}: ${JavaScriptContent.toJS(v._2)}").mkString("{ ", ", ", " }")
    eval(JavaScriptString(s"FabricJS.add('$id', '${o.id}', new fabric.${o.name}($props));"))
  }

  protected def eval(js: JavaScriptContent) = canvas.connected[Webpage[_]] {
    case webpage => webpage.eval(js)
  }
}

class Canvas(canvas: tag.Canvas) extends StaticCanvas(canvas) {
  override protected def className = "Canvas"
}

abstract class Object(val name: String) extends Listenable {
  val id = Unique()

  private var _properties = List.empty[ObjectProperty[_]]
  def properties = _properties

  protected def prop[T](name: String)(implicit manifest: Manifest[T]) = synchronized {
    val p = new ObjectProperty[T](name, this)(manifest)
    _properties = p :: _properties
    p
  }
}

class Rect extends Object("Rect") {
  lazy val left = prop[Double]("left")
  lazy val top = prop[Double]("top")
  lazy val fill = prop[Color]("fill")
  lazy val width = prop[Double]("width")
  lazy val height = prop[Double]("height")
}

class ObjectProperty[T](val name: String, o: Object)(implicit manifest: Manifest[T]) extends Property[T](default = None)(o, manifest)