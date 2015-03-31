package org.hyperscala.fabricjs

import org.powerscala.hierarchy.MutableContainer
import org.hyperscala.javascript.dsl._

import scala.reflect.ManifestFactory

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Group extends Object("Group") with MutableContainer[Object] {
  implicit val childManifest = ManifestFactory.classType[Object](classOf[Object])

  override protected[fabricjs] def addToCanvas(canvas: StaticCanvas, group: Option[Group]) = {
    super.addToCanvas(canvas, group)
    contents.foreach(_.addToCanvas(canvas, Some(this)))
    setCoords()
  }

  override protected[fabricjs] def construct = s"new fabric.$name([], $props)"

  def setCoords() = connected[StaticCanvas] {
    case canvas => canvas.eval(s"FabricJS.object['$id'].setCoords();")
  }
}