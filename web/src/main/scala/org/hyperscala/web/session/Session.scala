package org.hyperscala.web.session

import org.powerscala.concurrent.Time._
import org.powerscala.Updatable
import org.powerscala.concurrent.Time
import org.hyperscala.web.Website


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Session extends Updatable {
  protected[web] var lastCheckin = System.currentTimeMillis()

  def name = getClass.getName
  def timeout: Double = 30.minutes

  def map: Map[Any, Any]

  def apply[T](key: Any): T

  def get[T](key: Any): Option[T]

  def update(key: Any, value: Any): Unit

  def remove(key: Any): Unit

  def values: Iterable[Any]

  def clear(): Unit

  def dispose() = {}

  override def update(delta: Double) = {
    val elapsed = Time.fromMillis(System.currentTimeMillis() - lastCheckin)
    if (elapsed > timeout) {    // Timeout the session
      Website().destroySession()
    } else {
      super.update(delta)
      map.values.foreach {
        case u: Updatable => u.update(delta)
        case _ => // Ignore
      }
    }
  }
}
