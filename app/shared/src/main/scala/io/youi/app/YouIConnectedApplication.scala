package io.youi.app

import io.youi.app.ConnectCommunication.AutoConnectSynchronous
import io.youi.communication.Connection
import io.youi.net._

trait YouIConnectedApplication[C <: Connection] extends YouIApplication {
  def connectCommunication: ConnectCommunication = AutoConnectSynchronous
  def communicationPath: Path = path"/communication"
}