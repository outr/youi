package io.youi.example

import io.youi.app.{CommunicationManager, YouIApplication}
import io.youi.net.URL

trait ExampleApplication extends YouIApplication {
  val pixiJS: String = cached(URL("https://cdnjs.cloudflare.com/ajax/libs/pixi.js/4.5.0/pixi.min.js"))

  val example: CommunicationManager[ExampleCommunication] = connectivity.communication[ExampleCommunication]
  val simple: CommunicationManager[SimpleCommunication] = connectivity.communication[SimpleCommunication]
}