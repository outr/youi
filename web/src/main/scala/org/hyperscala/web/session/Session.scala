package org.hyperscala.web.session


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Session {
  def apply[T](name: String): T

  def get[T](name: String): Option[T]

  def update(name: String, value: Any): Unit

  def remove(name: String): Unit

  def clear(): Unit
}
