package org.hyperscala.ui.widgets.visual

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Stringify[T] {
  def toString(t: T): String
}
