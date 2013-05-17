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
           cx: java.lang.Double = null,
           cy: java.lang.Double = null,
           r: java.lang.Double = null) = {
    this()
    up(this.id, id)
    up(this.xmlBase, xmlBase)
    up(this.xmlLang, xmlLang)
    up(this.xmlSpace, xmlSpace)
    up(this.cx, cx)
    up(this.cy, cy)
    up(this.r, r)
  }

  lazy val cx = PropertyAttribute[Double]("cx", 0.0)
  lazy val cy = PropertyAttribute[Double]("cy", 0.0)
  lazy val r = PropertyAttribute[Double]("r", 0.0)
}
