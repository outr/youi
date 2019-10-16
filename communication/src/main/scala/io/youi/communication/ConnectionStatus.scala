package io.youi.communication

sealed trait ConnectionStatus

object ConnectionStatus {
  case object Disconnected extends ConnectionStatus
  case object Connecting extends ConnectionStatus
  case object Connected extends ConnectionStatus
  case object Disposed extends ConnectionStatus
}