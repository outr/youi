package io.youi.component.event

case class Dropped[T](mouseEvent: MouseEvent, value: T)