package youi.app

import youi.app.ConnectCommunication.AutoConnectSynchronous
import youi.communication.Connection
import spice.net._

trait YouIConnectedApplication[C <: Connection] extends YouIApplication {
  def connectCommunication: ConnectCommunication = AutoConnectSynchronous
  def communicationPath: Path = path"/communication"
}