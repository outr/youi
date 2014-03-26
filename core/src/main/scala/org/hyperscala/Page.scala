package org.hyperscala

import org.powerscala.event.Listenable
import org.powerscala.Updatable
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Page extends Listenable with Updatable with Logging {
  protected def parentIntercept: MarkupIntercepting = null

  def rendered: Boolean
  val intercept = new MarkupIntercepting(parentIntercept)

  def require(name: String): Unit

  override def update(delta: Double) {
    super.update(delta)

    intercept.update.fire(this)
  }
}