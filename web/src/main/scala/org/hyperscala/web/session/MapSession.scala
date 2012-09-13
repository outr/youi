package org.hyperscala.web.session

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class MapSession extends Session {
  var map = Map.empty[Any, Any]

  def apply[T](key: Any) = map(key).asInstanceOf[T]

  def get[T](key: Any) = map.get(key).asInstanceOf[Option[T]]

  def update(key: Any, value: Any) = if (value != null) {
    map += key -> value
  } else {
    remove(key)
  }

  def remove(key: Any) = map -= key

  def clear() = map = Map.empty

  def values = map.values
}