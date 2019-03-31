package io.youi.example

import com.outr.hookup.server

import scala.concurrent.Future

trait SimpleCommunication {
  @server def reverse(text: String): Future[String]
}