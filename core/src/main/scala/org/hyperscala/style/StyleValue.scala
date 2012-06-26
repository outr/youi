package com.outr.webframework.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait StyleValue {
  def value: String

  override def toString = value
}
