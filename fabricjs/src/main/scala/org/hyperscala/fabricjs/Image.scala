package org.hyperscala.fabricjs

import org.hyperscala.javascript.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Image(val url: String) extends Object("Image") {
  lazy val alignX = prop("alignX", "none")
  lazy val alignY = prop("alignY", "none")
  lazy val crossOrigin = prop("crossOrigin", "")
  lazy val meetOrSlice = prop("meetOrSlice", "meet")

  override protected[fabricjs] def addToCanvas(canvas: StaticCanvas) = {
    val js =
      s"""fabric.Image.fromURL('$url', function(image) {
         |  image.set(${props});
         |  FabricJS.add('${canvas.id}', '$id', image);
         |});""".stripMargin
    canvas.eval(js)
  }
}
