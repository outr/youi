package io.youi.http

trait ListTypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): List[V]

  def apply(values: List[V], headers: Headers, append: Boolean = false): Headers = {
    var map = headers.map
    val list = values.map { v =>
      val h = apply(v)
      h.value
    }
    if (append) {
      map += key -> (map.getOrElse(key, Nil) ::: list)
    } else {
      map += key -> list
    }
    Headers(map)
  }

  def apply(value: V): Header
}
