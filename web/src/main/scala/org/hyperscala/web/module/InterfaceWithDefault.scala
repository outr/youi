package org.hyperscala.web.module

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class InterfaceWithDefault(interface: Interface, default: Module) extends Interface {
  def name = interface.name
}
