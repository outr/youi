package org.hyperscala.module

import org.powerscala.Version

/**
 * Module allows for introduction of functionality into a webpage / website with minimal coupling. A framework may
 * "require" a Module when leveraging components in order to provide better integration.
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Module extends Interface {
  /**
   * The name of this Module. The name combined with the version is used to determine duplicate modules for a page.
   */
  def name: String

  /**
   * A unique version for the same Module name will defer to the highest module version.
   */
  def version: Version

  /**
   * Defines any interfaces this Module may implement.
   */
  def implements: List[Interface] = Nil

  /**
   * Defines any dependencies necessary for this Module to function.
   */
  def dependencies: List[Interface] = Nil

  /**
   * Init is invoked only once per Website and is guaranteed to occur before "load".
   */
  def init(): Unit

  /**
   * Load is invoked only once per Webpage and is guaranteed to occur after "init".
   */
  def load(): Unit

  override def toString() = "Module(name = %s, version = %s)".format(name, version)
}