package org.hyperscala

import org.powerscala.event.Listenable
import org.powerscala.bus.Bus
import org.powerscala.{Updatable, Priority}
import module.{Module, Interface}
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page extends Listenable with Updatable with Logging {
  override val bus = new Bus(Priority.Normal)
  Bus.current = bus

  Page.instance.set(this)

  protected def parentIntercept: MarkupIntercepting = null

  val intercept = new MarkupIntercepting("Page", bus, parentIntercept)

  def require(interface: Interface): Unit

  def require(interface: Interface, default: Module): Unit

  def require(name: String): Unit = require(Interface(name))

  override def update(delta: Double) {
    super.update(delta)

    intercept.update.fire(this)
  }
}

object Page {
  val instance = new ThreadLocal[Page]

  def apply() = instance.get()
}