package org.hyperscala.examples.basic

import org.hyperscala.WebPage
import org.hyperscala.svg.{Circle, Svg}
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object SVGExample extends WebPage("/examples/svg.html") {
  body.contents += new Svg {
    contents += new Circle(cx = 100, cy = 50, r = 40, stroke = Color.Black, strokeWidth = 2, fill = Color.Red)
  }
}
