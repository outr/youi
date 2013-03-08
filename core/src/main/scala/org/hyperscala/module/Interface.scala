package org.hyperscala.module

/**
 * Interfaces exist as named modularity that are implemented by Modules and required by Webpages.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait Interface {
  def name: String

  override def toString = "Interface(%s)".format(name)
}

object Interface {
  def apply(name: String) = NamedInterface(name)
}

case class NamedInterface(name: String) extends Interface