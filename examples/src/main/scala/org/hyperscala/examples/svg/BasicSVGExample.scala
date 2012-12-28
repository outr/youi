package org.hyperscala.examples.svg

import org.hyperscala.examples.Example
import org.hyperscala.svg.{Circle, Svg}
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class BasicSVGExample extends Example {
  contents += new Svg {
    contents += new Circle {
      cx := 100
      cy := 50
      r := 40
      stroke := Color.Black
      strokeWidth := 2
      fill := Color.Red
    }
  }

  def sourceURL = "https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/org/hyperscala/examples/svg/BasicSVGExample.scala"
}
