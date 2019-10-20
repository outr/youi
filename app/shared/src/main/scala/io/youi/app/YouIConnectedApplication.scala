package io.youi.app

import io.youi.communication.Connection
import io.youi.net._

trait YouIConnectedApplication[C <: Connection] extends YouIApplication {
  def autoConnectCommunication: Boolean = true
  def communicationPath: Path = path"/communication"
}