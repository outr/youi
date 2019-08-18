package io.youi.app

sealed trait ReconnectStrategy

object ReconnectStrategy {
  case object Reload extends ReconnectStrategy
  case object Stop extends ReconnectStrategy
  case object Reconnect extends ReconnectStrategy
}