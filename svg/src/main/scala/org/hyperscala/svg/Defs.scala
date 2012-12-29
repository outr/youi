package org.hyperscala.svg

import traits.{Shape, SVGContainer}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Defs extends SVGContainer[SVGTag] with Shape {
  lazy val xmlLabel = "defs"
}