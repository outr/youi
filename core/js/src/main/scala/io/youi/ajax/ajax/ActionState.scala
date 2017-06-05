package io.youi.ajax.ajax

sealed trait ActionState

object ActionState {
  case object New extends ActionState
  case object Enqueued extends ActionState
  case object Running extends ActionState
  case object Finished extends ActionState
}