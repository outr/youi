package io.youi.example

import io.youi.app.{ApplicationCommunication, CommunicationManager, YouIApplication}

trait ExampleApplication extends YouIApplication {
  val comm = new ExampleComms(this)
}

class ExampleComms(application: ExampleApplication) extends ApplicationCommunication(application) {
  val example: CommunicationManager[ExampleCommunication] = communication[ExampleCommunication]
  val simple: CommunicationManager[SimpleCommunication] = communication[SimpleCommunication]
}