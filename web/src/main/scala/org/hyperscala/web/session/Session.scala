package org.hyperscala.web.session

import org.powerscala.concurrent.Time._
import org.powerscala.{Logging, Updatable}
import org.powerscala.concurrent.WorkQueue
import org.powerscala.event.{Listenable, Event}
import org.hyperscala.web.site._
import scala.Some
import org.hyperscala.context.Contextual


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
// TODO: re-architect so it can be a case class or class with Properties
// TODO: make pages first-class citizens
trait Session extends Temporal with Listenable with WorkQueue {
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

  def getOrSet[T](key: Any, default: => T) = get[T](key) match {
    case Some(value) => value
    case None => {
      val v: T = default
      update(key, v)
      v
    }
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
        case u: Updatable => u match {
          case contextual: Contextual => WebContext.wrap(contextual) {
            u.update(delta)
          }
          case _ => u.update(delta)
        }
        case _ => // Ignore
      }
    }
  }

  override def dispose() = {
    super.dispose()

    Website().destroySession(this)
    map.values.foreach {
      case d: Disposable => d match {
        case contextual: Contextual => WebContext.wrap(contextual) {
          d.dispose()
        }
        case _ => d.dispose()
      }
      case _ => // Not disposable
    }
  }
}

object Session extends Logging