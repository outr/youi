package org.hyperscala.svg

import org.hyperscala.WebAttribute
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Circle extends Shape {
  def this(id: String = null,
           xmlBase: String = null,
           xmlLang: String = null,
           xmlSpace: String = null,
           stroke: Color = null,
           strokeWidth: java.lang.Integer = null,
           fill: Color = null,
           cx: java.lang.Integer = null,
           cy: java.lang.Integer = null,
           r: java.lang.Integer = null) = {
    this()
    up(this.id, id)
    up(this.xmlBase, xmlBase)
    up(this.xmlLang, xmlLang)
    up(this.xmlSpace, xmlSpace)
    up(this.stroke, stroke)
    if (strokeWidth != null) {
      this.stroke.width := strokeWidth.intValue()
    }
    up(this.fill, fill)
    up(this.cx, cx)
    up(this.cy, cy)
    up(this.r, r)
  }

  def tag = "circle"

  val cx = WebAttribute[Int]("cx")
  val cy = WebAttribute[Int]("cy")
  val r = WebAttribute[Int]("r")
}
