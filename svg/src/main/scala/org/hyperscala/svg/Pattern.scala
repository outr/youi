package org.hyperscala.svg

import org.hyperscala.svg.traits.{Presentation, SVGContainer}
import org.hyperscala.PropertyAttribute
import org.hyperscala.css.attributes.Length
import org.hyperscala.svg.attributes.{ViewBox, PatternUnits}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Pattern extends SVGContainer[SVGTag] with SVGTag with Presentation {
  lazy val xmlLabel = "pattern"

  lazy val patternUnits = PropertyAttribute[PatternUnits]("patternUnits", null)
  lazy val patternContentUnits = PropertyAttribute[PatternUnits]("patternContentUnits", null)
  lazy val x = PropertyAttribute[Double]("x", 0.0)
  lazy val y = PropertyAttribute[Double]("y", 0.0)
  lazy val width = PropertyAttribute[Length]("width", null)
  lazy val height = PropertyAttribute[Length]("height", null)
  lazy val viewBox = PropertyAttribute[ViewBox]("viewBox", null)
}
