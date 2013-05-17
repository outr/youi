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

  lazy val x1 = PropertyAttribute[Length]("x1", null)
  lazy val y1 = PropertyAttribute[Length]("y1", null)
  lazy val x2 = PropertyAttribute[Length]("x2", null)
  lazy val y2 = PropertyAttribute[Length]("y2", null)
  lazy val gradientUnits = PropertyAttribute[String]("gradientUnits", null)
  lazy val gradientTransform = PropertyAttribute[List[Transform]]("gradientTransform", Nil)
  lazy val spreadMethod = PropertyAttribute[String]("spreadMethod", null)
}
