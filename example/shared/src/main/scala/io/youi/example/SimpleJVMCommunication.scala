package io.youi.example

import io.youi.communication.{Communication, server}

import scala.concurrent.Future

trait SimpleJVMCommunication extends Communication {
  @server def reverse(text: String): Future[String]
}
