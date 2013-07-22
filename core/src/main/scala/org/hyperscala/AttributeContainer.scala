package org.hyperscala

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait AttributeContainer[A <: PropertyAttribute[_]] {
  protected var _attributes: Map[String, A] = _

  def addAttribute(attribute: A) = synchronized {
    if (_attributes == null) {
      _attributes = Map.empty
    }
    _attributes += attribute.name -> attribute
  }
  def getAttribute(name: String) = if (_attributes != null) {
    _attributes.get(name)
  } else {
    None
  }

  def attributes = if (_attributes != null) {
    _attributes
  } else {
    Map.empty
  }
}
