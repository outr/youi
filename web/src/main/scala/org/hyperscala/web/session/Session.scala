package org.hyperscala.web.session

import org.powerscala.concurrent.Time._
import org.powerscala.{Logging, Updatable}
import org.powerscala.concurrent.WorkQueue
import org.powerscala.event.{Listenable, Event}
import org.hyperscala.web.site._
import scala.Some


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
// TODO: re-architect so it can be a case class or class with Properties
// TODO: make pages first-class citizens
trait Session extends Temporal with Listenable with WorkQueue {
  def website: Website[_]

  def name = getClass.getName

  /**
   * The timeout for this session in seconds without any communication.
   *
   * Defaults to 30 minutes.
   */
  def timeout: Double = 30.minutes

  def map: Map[Any, Any]

  def apply[T](key: Any): T

  def get[T](key: Any): Option[T]

  def getOrElse[T](key: Any, default: => T) = get[T](key) match {
    case Some(value) => value
    case None => default
  }

  def update(key: Any, value: Any): Unit

  def remove(key: Any): Boolean

  def removeByValue(value: Any) = iterator.find {
    case (k, v) => v == value
  } match {
    case Some((k, v)) => remove(k)
    case None => false
  }

  def iterator: Iterator[(Any, Any)]

  def values: Iterable[Any]

  def clear(): Unit

  def fireLater(event: Event) = {
    WorkQueue.enqueue(this, () => fire(event))
  }

  def invokeLater(f: => Unit) = {
    WorkQueue.enqueue(this, () => f)
  }

  override def update(delta: Double) = {
    super.update(delta)
    if (!disposed) {
      doAllWork()
      map.values.foreach {
        case page: Webpage => WebContext(page.webContext, checkIn = false) {
          page.update(delta)
        }
        case u: Updatable => u.update(delta)
        case _ => // Ignore
      }
    }
  }

  override def dispose() = {
    super.dispose()

    website.destroySession(this)
    map.values.foreach {
      case page: Webpage => WebContext(page.webContext, checkIn = false) {
        page.dispose()
      }
      case d: Disposable => d.dispose()
      case _ => // Not disposable
    }
  }
}

object Session extends Logging