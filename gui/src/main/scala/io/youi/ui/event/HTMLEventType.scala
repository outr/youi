package io.youi.ui.event

sealed trait HTMLEventType

object HTMLEventType {
  case object Mouse extends HTMLEventType
  case object Pointer extends HTMLEventType
  case object Touch extends HTMLEventType
}