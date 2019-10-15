package io.youi.communication

import reactify.{Val, Var}

trait Connection {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Disconnected)

  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = _status
}

sealed trait ConnectionStatus

object ConnectionStatus {
  case object Disconnected extends ConnectionStatus
  case object Connecting extends ConnectionStatus
  case object Connected extends ConnectionStatus
  case object Disposed extends ConnectionStatus
}