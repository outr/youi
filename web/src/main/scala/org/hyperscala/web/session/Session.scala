package org.hyperscala.web.session

import org.powerscala.concurrent.Time._
import org.powerscala.{Logging, Updatable}
import org.powerscala.concurrent.{WorkQueue, Time}
import org.hyperscala.web.Website
import org.powerscala.event.{Listenable, Event}


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
// TODO: re-architect so it can be a case class or class with Properties
// TODO: make pages first-class citizens
trait Session extends Updatable with Listenable with WorkQueue {
  val created = System.currentTimeMillis()
  protected[web] var lastCheckin = created
  def lifetime = Time.fromMillis(System.currentTimeMillis() - created)

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
    val elapsed = lifetime
    doAllWork()
    if (elapsed > timeout) {    // Timeout the session
      Session.info("Session has timed out; Elapsed: %s, Timeout: %s, Session: %s".format(elapsed, timeout, this))
      Website().destroySession(this)
    } else {
      super.update(delta)
      map.values.foreach {
        case u: Updatable => u.update(delta)
        case _ => // Ignore
      }
    }
  }
}

object Session extends Logging