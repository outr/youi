package org.hyperscala.svg

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Svg extends SvgTag {
  def tag = "svg"

  val xmlns = WebAttribute[String]("xmlns", "http://www.w3.org/2000/svg")
  val version = WebAttribute[String]("version", "1.1")
}
