package io.youi.communication

import reactify.{Val, Var}

import scala.language.experimental.macros

trait Connection {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Disconnected)

  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = _status

  protected def apply[Interface](name: String): Interface with Hookup[Interface] = macro HookupMacros.interface[Interface]
  protected def apply[Interface, Implementation <: Interface](name: String): Implementation with Hookup[Interface] = macro HookupMacros.implementation[Interface, Implementation]
}

sealed trait ConnectionStatus

object ConnectionStatus {
  case object Disconnected extends ConnectionStatus
  case object Connecting extends ConnectionStatus
  case object Connected extends ConnectionStatus
  case object Disposed extends ConnectionStatus
}