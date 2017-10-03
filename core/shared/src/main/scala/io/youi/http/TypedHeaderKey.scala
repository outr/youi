package io.youi.http

trait TypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): Option[V]

  def apply(value: V): Header
}
