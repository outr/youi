package org.hyperscala.svg

import org.hyperscala.{InclusionMode, PropertyAttribute, Container}
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.HTMLTag

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

  val xmlns = PropertyAttribute[String]("xmlns", "http://www.w3.org/2000/svg", inclusion = InclusionMode.Always)
  val version = PropertyAttribute[String]("version", "1.1", inclusion = InclusionMode.Always)
}
