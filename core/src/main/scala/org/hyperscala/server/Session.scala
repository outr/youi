package org.hyperscala.server


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Session {
  private var map = Map.empty[String, Any]

  def apply[T](name: String) = map(name).asInstanceOf[T]

  def get[T](name: String) = map.get(name).asInstanceOf[Option[T]]

  def update(name: String, value: Any) = map += name -> value
}

object Session {
  private val sessions = new ThreadLocal[Session]

  def apply(session: Session)(f: => Any) = {
    sessions.set(session)
    try {
      f
    } finally {
      sessions.set(null)
    }
  }

  def apply() = sessions.get()
}
