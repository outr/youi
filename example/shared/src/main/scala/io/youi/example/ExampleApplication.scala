package io.youi.example

import io.youi.app.{CommunicationManager, YouIApplication}

trait ExampleApplication extends YouIApplication {
  val example: CommunicationManager[ExampleCommunication] = createCommunication[ExampleCommunication](connectivity)
  val simple: CommunicationManager[SimpleCommunication] = createCommunication[SimpleCommunication](connectivity)
}