package io.youi.example

import io.youi.app.{CommunicationManager, YouIApplication}

trait ExampleApplication extends YouIApplication {
  val comm: CommunicationManager[ExampleCommunication] = communication[ExampleCommunication]
  val simple: CommunicationManager[SimpleJVMCommunication] = communication[SimpleJVMCommunication]
}