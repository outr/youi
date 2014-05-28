package org.hyperscala.javascript

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JSObjectWithDefault extends JSObject {
  def default: JSObject
}
