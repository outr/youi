package org.hyperscala.fabricjs

import org.hyperscala.html.tag
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.web.Webpage
import org.powerscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
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