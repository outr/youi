package youi.app

sealed trait ConnectCommunication

object ConnectCommunication {
  case object AutoConnectSynchronous extends ConnectCommunication
  case object AutoConnectAsynchronous extends ConnectCommunication
  case object ManualConnect extends ConnectCommunication
}