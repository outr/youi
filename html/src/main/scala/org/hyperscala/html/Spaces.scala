package org.hyperscala.html

import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.Listenable
import scala.collection.immutable.ListMap

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Spaces[K, V] extends Listenable {
  private var spaces = ListMap.empty[K, V]

  implicit def keyManifest: Manifest[K]
  implicit def valueManifest: Manifest[V]

  val removed = new UnitProcessor[SpaceValueRemoved[K, V]]("removed")

  def get(key: K) = spaces.get(key)
  def apply(key: K) = spaces(key)
  def remove(key: K): Option[V] = synchronized {
    val option = get(key)
    spaces -= key
    option match {
      case Some(value) => removed.fire(SpaceValueRemoved(key, value))
      case _ => // Nothing removed
    }
    option
  }

  def update(key: K, value: V) = synchronized {
    spaces += key -> value
  }

  protected def modified(key: K, value: V): Unit = {}

  def map = spaces
  def values = spaces.values
}

case class SpaceValueRemoved[K, V](key: K, value: V)