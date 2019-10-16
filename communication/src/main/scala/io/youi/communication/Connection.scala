package io.youi.communication

import reactify.{Val, Var}

import scala.language.experimental.macros

trait Connection {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Disconnected)

  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = _status

  protected def interface[Interface]: Interface with Hookup[Interface] = macro HookupMacros.interface[Interface]
  protected def implementation[Interface, Implementation <: Interface]: Implementation with Hookup[Interface] = macro HookupMacros.implementation[Interface, Implementation]
}

sealed trait ConnectionStatus

object ConnectionStatus {
  case object Disconnected extends ConnectionStatus
  case object Connecting extends ConnectionStatus
  case object Connected extends ConnectionStatus
  case object Disposed extends ConnectionStatus
}