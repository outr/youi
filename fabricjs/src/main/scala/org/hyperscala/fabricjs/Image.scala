package org.hyperscala.fabricjs

import org.hyperscala.fabricjs.filters.BaseFilter
import org.hyperscala.javascript.dsl._
import org.powerscala.hierarchy.MutableContainer
import org.powerscala.property.{ListProperty, Property}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Image(val url: String) extends Object("Image") {
  lazy val alignX = prop("alignX", "none")
  lazy val alignY = prop("alignY", "none")
  lazy val crossOrigin = prop("crossOrigin", "")
  lazy val meetOrSlice = prop("meetOrSlice", "meet")
  val filters = new ImageFilters(this)

  override protected[fabricjs] def addToCanvas(canvas: StaticCanvas) = {
    val filtersJS = filters.contents.toList.map(bf => bf.addFilterJS(this)).mkString("\r\n  ")
    val reRender = if (filters.contents.nonEmpty) {
      s"image.applyFilters(canvas.renderAll.bind(canvas));"
    } else {
      ""
    }
    val js =
      s"""fabric.Image.fromURL('$url', function(image) {
         |  image.set(${props});
         |  var canvas = FabricJS.canvas['${canvas.id}'];
         |  $filtersJS
         |  FabricJS.add('${canvas.id}', '$id', image);
         |  $reRender
         |});""".stripMargin
    canvas.eval(js)
  }
}

class ImageFilters(image: Image) extends MutableContainer[BaseFilter] {
  childAdded.on {
    case evt => image.canvas match {
      case Some(canvas) => {
        val filter = evt.child.asInstanceOf[BaseFilter]
        val js =
          s"""var canvas = FabricJS.canvas['${canvas.id}'];
            |var image = FabricJS.object['${image.id}'];
            |${filter.addFilterJS(image)}
            |image.applyFilters(canvas.renderAll.bind(canvas));""".stripMargin
        canvas.eval(js)
      }
      case None => // Ignore
    }
  }
  childRemoved.on {
    case evt => image.canvas match {
      case Some(canvas) => {
        val filter = evt.child.asInstanceOf[BaseFilter]
        val js =
          s"""var canvas = FabricJS.canvas['${canvas.id}'];
              |var image = FabricJS.object['${image.id}'];
              |FabricJS.removeFilter(image, '${filter.id}');
              |image.applyFilters(canvas.renderAll.bind(canvas));""".stripMargin
        canvas.eval(js)
      }
      case None => // Ignore
    }
  }
}