package org.hyperscala.context

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
private[context] class ContextInstance(var outer: ContextInstance) {
  private var map = Map.empty[String, Any]

  def apply(name: String): Any = if (!map.contains(name) && outer != null) {
    outer(name)
  } else {
    map(name)
  }

  def get(name: String): Any = if (!map.contains(name) && outer != null && outer != this) {
    outer.get(name)
  } else {
    map.get(name)
  }

  def getOrElse(name: String, default: => Any): Any = if (!map.contains(name) && outer != null && outer != this) {
    outer.getOrElse(name, default)
  } else {
    map.getOrElse(name, default)
  }

  def update(name: String, value: Any) = map += name -> value
}
