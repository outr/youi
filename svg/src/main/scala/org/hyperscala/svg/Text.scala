package org.hyperscala.svg

import traits.Shape
import org.hyperscala.{PropertyAttribute, Textual}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Text extends Shape with Textual {
  lazy val xmlLabel = "text"

  val x = PropertyAttribute[Double]("x", 0.0)
  val y = PropertyAttribute[Double]("y", 0.0)
  val dx = PropertyAttribute[List[Double]]("dx", Nil)
  val dy = PropertyAttribute[List[Double]]("dy", Nil)
  val rotate = PropertyAttribute[List[Double]]("rotate", Nil)
  val textLength = PropertyAttribute[String]("textLength", null)
  val lengthAdjust = PropertyAttribute[String]("lengthAdjust", null)
}
