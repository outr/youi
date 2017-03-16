package io.youi.example

import io.youi.app.{CommunicationManager, YouIApplication}

trait ExampleApplication extends YouIApplication {
  val example: CommunicationManager[ExampleCommunication] = connectivity.communication[ExampleCommunication]
  val simple: CommunicationManager[SimpleCommunication] = connectivity.communication[SimpleCommunication]
}