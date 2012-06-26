package com.outr.webframework.tags.attributes

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait AttributeValue {
  def value: String

  override def toString = value
}
