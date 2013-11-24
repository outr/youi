package org.hyperscala

import scala.collection.immutable.ListMap

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait AttributeContainer[A <: PropertyAttribute[_]] {
  protected var _attributes: ListMap[String, A] = _

  def addAttribute(attribute: A) = synchronized {
    if (_attributes == null) {
      _attributes = ListMap.empty
    }
    _attributes += attribute.name -> attribute
  }
  def getAttribute(name: String) = if (_attributes != null) {
    _attributes.get(name)
  } else {
    None
  }

  def attributes: Map[String, A] = if (_attributes != null) {
    _attributes
  } else {
    Map.empty
  }
}
