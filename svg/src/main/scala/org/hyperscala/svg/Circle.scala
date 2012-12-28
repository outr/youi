package org.hyperscala.svg

import attributes.XMLSpace
import org.hyperscala.PropertyAttribute
import traits.Shape

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Circle extends Shape {
  lazy val xmlLabel = "circle"

  def this(id: String = null,
           xmlBase: String = null,
           xmlLang: String = null,
           xmlSpace: XMLSpace = null,
           cx: java.lang.Integer = null,
           cy: java.lang.Integer = null,
           r: java.lang.Integer = null) = {
    this()
    up(this.id, id)
    up(this.xmlBase, xmlBase)
    up(this.xmlLang, xmlLang)
    up(this.xmlSpace, xmlSpace)
    up(this.cx, cx)
    up(this.cy, cy)
    up(this.r, r)
  }

  val cx = PropertyAttribute[Int]("cx", 0)
  val cy = PropertyAttribute[Int]("cy", 0)
  val r = PropertyAttribute[Int]("r", 0)
}
