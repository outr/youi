package org.hyperscala.examples.basic

import org.hyperscala.WebSite

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object BasicExamplesSite extends WebSite {
  def default = SVGExample

  val svgExample = SVGExample
}
