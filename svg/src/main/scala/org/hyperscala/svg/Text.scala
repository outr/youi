package org.hyperscala.svg

import traits.Shape
import org.hyperscala.{PropertyAttribute, Textual}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Text extends Shape with Textual {
  lazy val xmlLabel = "text"

  lazy val x = PropertyAttribute[Double]("x", 0.0)
  lazy val y = PropertyAttribute[Double]("y", 0.0)
  lazy val dx = PropertyAttribute[List[Double]]("dx", Nil)
  lazy val dy = PropertyAttribute[List[Double]]("dy", Nil)
  lazy val rotate = PropertyAttribute[List[Double]]("rotate", Nil)
  lazy val textLength = PropertyAttribute[String]("textLength", null)
  lazy val lengthAdjust = PropertyAttribute[String]("lengthAdjust", null)
}
