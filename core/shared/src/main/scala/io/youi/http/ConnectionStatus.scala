package io.youi.http

sealed trait ConnectionStatus {
  lazy val name: String = getClass.getSimpleName.replaceAllLiterally("$", "")
}

object ConnectionStatus {
  case object Closed extends ConnectionStatus
  case object Closing extends ConnectionStatus
  case object Connecting extends ConnectionStatus
  case object Open extends ConnectionStatus
}