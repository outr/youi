package io.youi.communicate

trait Pickler[T] {
  def read(json: String): T
  def write(t: T): String
}
