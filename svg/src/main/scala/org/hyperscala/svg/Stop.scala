package org.hyperscala.svg

import traits.Presentation
import org.hyperscala.PropertyAttribute
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Stop extends SVGTag with Presentation {
  lazy val xmlLabel = "stop"

  val offset = PropertyAttribute[Length]("offset", null)
}
