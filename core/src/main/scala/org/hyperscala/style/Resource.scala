package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Resource private(val value: String) extends StyleValue

object Resource {
  val None = new Resource("none")
  val Inherit = new Resource("inherit")
  def apply(url: String) = new Resource("url('%s')".format(url))
}
