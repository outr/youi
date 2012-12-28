package org.hyperscala.svg

import org.hyperscala.{InclusionMode, PropertyAttribute, Container}
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html._
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Svg extends Container[SVGTag] with BodyChild with HTMLTag {
  lazy val xmlLabel = "svg"

  def this(xmlns: String = null,
           version: String = null,
           content: SVGTag = null) = {
    this()
    up(this.xmlns, xmlns)
    up(this.version, version)
    if (content != null) contents += content
  }

  val version = PropertyAttribute[String]("version", "1.1", inclusion = InclusionMode.Always)
  val baseProfile = PropertyAttribute[String]("baseProfile", null)
  val x = PropertyAttribute[Int]("x", 0)
  val y = PropertyAttribute[Int]("y", 0)
  val width = PropertyAttribute[Length]("width", 100.pct)
  val height = PropertyAttribute[Length]("height", null)
  val preserveAspectRatio = PropertyAttribute[String]("preserveAspectRatio", null)
  val contentScriptType = PropertyAttribute[String]("contentScriptType", null)
  val contentStyleType = PropertyAttribute[String]("contentStyleType", null)
  val zoomAndPan = PropertyAttribute[String]("zoomAndPan", null)
  val xmlns = PropertyAttribute[String]("xmlns", "http://www.w3.org/2000/svg", inclusion = InclusionMode.Always)
}
