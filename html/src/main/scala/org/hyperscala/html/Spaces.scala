package org.hyperscala.html

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Spaces[T] {
  private var spaces = Map.empty[String, T]

  def get(key: String) = spaces.get(key)
  def apply(key: String) = spaces(key)

  def update(key: String, value: T) = synchronized {
    spaces += key -> value
  }

  protected def modified(key: String, value: T): Unit = {}

  def map = spaces
}
