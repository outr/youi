package org.hyperscala.module

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
case class InterfaceWithDefault(interface: Interface, default: Module) extends Interface {
  def name = interface.name
}
