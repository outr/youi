package org.hyperscala.web.session

import org.powerscala.concurrent.Time._
import org.powerscala.Updatable
import org.powerscala.concurrent.{WorkQueue, Time}
import org.hyperscala.web.Website
import org.powerscala.event.{Listenable, Event}


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Session extends Updatable with Listenable with WorkQueue {
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

  def fireLater(event: Event) = {
    WorkQueue.enqueue(this, () => fire(event))
  }

  def invokeLater(f: => Unit) = {
    WorkQueue.enqueue(this, () => f)
  }

  override def update(delta: Double) = {
    val elapsed = Time.fromMillis(System.currentTimeMillis() - lastCheckin)
    doAllWork()
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
