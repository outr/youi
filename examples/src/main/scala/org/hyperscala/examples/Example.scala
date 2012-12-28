package org.hyperscala.examples

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Example extends tag.Div {
  def exampleName = getClass.getSimpleName

  def sourceURL: String
}
