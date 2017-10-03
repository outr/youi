package io.youi.http

trait MultiTypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): List[V]

  def apply(values: V*): Header
}
