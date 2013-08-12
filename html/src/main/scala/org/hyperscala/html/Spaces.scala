package org.hyperscala.html

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Spaces[K, V] {
  private var spaces = Map.empty[K, V]

  def get(key: K) = spaces.get(key)
  def apply(key: K) = spaces(key)

  def update(key: K, value: V) = synchronized {
    spaces += key -> value
  }

  protected def modified(key: K, value: V): Unit = {}

  def map = spaces
}
