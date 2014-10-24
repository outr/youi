package org.hyperscala.html.extension

/**
 * ClassValue is used to represent an optional CSS class name in class properties.
 *
 * @see ClassBooleanProperty
 * @see ClassOptionProperty
 * @see ClassProperty
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait ClassName {
  def className: Option[String]
}