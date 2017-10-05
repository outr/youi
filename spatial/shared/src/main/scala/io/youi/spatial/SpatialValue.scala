package io.youi.spatial

trait SpatialValue[T] {
  def isMutable: Boolean
  def mutable: T
  def immutable: T
}