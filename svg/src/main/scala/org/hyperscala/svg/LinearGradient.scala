package org.hyperscala.svg

import attributes.Transform
import traits.{SVGContainer, Presentation}
import org.hyperscala.PropertyAttribute
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class LinearGradient extends SVGContainer[SVGTag] with SVGTag with Presentation {
  lazy val xmlLabel = "linearGradient"

  val x1 = PropertyAttribute[Length]("x1", null)
  val y1 = PropertyAttribute[Length]("y1", null)
  val x2 = PropertyAttribute[Length]("x2", null)
  val y2 = PropertyAttribute[Length]("y2", null)
  val gradientUnits = PropertyAttribute[String]("gradientUnits", null)
  val gradientTransform = PropertyAttribute[List[Transform]]("gradientTransform", Nil)
  val spreadMethod = PropertyAttribute[String]("spreadMethod", null)
}
