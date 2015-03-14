package org.hyperscala

import org.powerscala.concurrent.Temporal
import org.powerscala.event.Listenable
import org.powerscala.log.Logging
import org.powerscala.property.ReadOnlyPropertyLense

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Page extends Listenable with Temporal with Logging {
  protected def parentIntercept: MarkupIntercepting = null

  def rendered: ReadOnlyPropertyLense[Boolean]
  val intercept = new MarkupIntercepting(parentIntercept)

  def require(name: String): Unit

  override def update(delta: Double) {
    super.update(delta)

    intercept.update.fire(delta)
  }
}