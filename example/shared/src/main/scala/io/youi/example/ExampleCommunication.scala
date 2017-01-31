package io.youi.example

import com.outr.reactify.Var
import io.youi.communication.{Communication, client, server}

import scala.concurrent.Future
import com.outr.scribe._

trait ExampleCommunication extends Communication {
  val name: Var[Option[String]] = shared[Option[String]](None)
  @client def url: Future[String]
  @server def reverse(value: String): Future[String]
  @server def time: Future[Long]
  @client def navigateTo(url: String, push: Boolean): Future[Unit]
  @server def counter: Future[Int]
  @server def broadcast(message: String): Future[Unit]
  @client def show(message: String): Future[Unit]

  name.attach { value =>
    logger.info(s"Name changed: $value")
  }
}