package org.hyperscala.site

import org.hyperscala.WebSite
import org.hyperscala.examples.basic.{SVGExample, POSTExample}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends WebSite {
  def default = about

  val about = HyperscalaAbout

  val postExample = POSTExample
  val svgExample = SVGExample
}
