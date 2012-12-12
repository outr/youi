package org.hyperscala.context

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
private[context] class ContextInstance(var outer: ContextInstance) {
  var map = Map.empty[String, Any]
}
