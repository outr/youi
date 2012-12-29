package org.hyperscala.svg

import traits.{SVGContainer, Shape}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class G extends SVGContainer[SVGTag] with Shape {
  lazy val xmlLabel = "g"
}
