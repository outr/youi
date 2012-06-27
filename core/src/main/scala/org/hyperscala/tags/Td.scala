package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Td extends Tag {
  def tag = "td"

  val colSpan = WebAttribute[String]("colspan")
  val headers = WebAttribute[String]("headers")
  val rowSpan = WebAttribute[String]("rowspan")
}
