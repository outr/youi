package org.hyperscala

import org.powerscala.event.Listenable
import org.powerscala.bus.Bus
import org.powerscala.{Updatable, Priority}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page extends Listenable with Updatable {
  override val bus = new Bus(Priority.Normal)
  Bus.current = bus

  Page.instance.set(this)

  protected def parentIntercept: MarkupIntercepting = null

  val intercept = new MarkupIntercepting("Page", bus, parentIntercept)

  override def update(delta: Double) {
    super.update(delta)

    intercept.update.fire(this)
  }
}

object Page {
  val instance = new ThreadLocal[Page]

  def apply() = instance.get()
}