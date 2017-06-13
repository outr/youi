package io.youi.component.event

case class Dropped[T](pointer: Pointer, value: T)