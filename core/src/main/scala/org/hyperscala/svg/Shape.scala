package org.hyperscala.svg

import org.hyperscala.{GenericAttribute, WebAttribute}
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait Shape extends SvgTag {
  val stroke = new GenericAttribute[Color]("stroke") {
    val width = WebAttribute[Int]("stroke-width")
  }

  val fill = WebAttribute[Color]("fill")
}
