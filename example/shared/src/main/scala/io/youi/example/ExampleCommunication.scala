package io.youi.example

import com.outr.reactify.Var
import io.youi.communication.{Communication, client, server}

import scala.concurrent.Future
import com.outr.scribe._

trait ExampleCommunication extends Communication {
  val name: Var[Option[String]] = shared[Option[String]](None)          // Shared Var
  @client def url: Future[String]                                       // Client call
  @server def reverse(value: String): Future[String]                    // Server method
  @server def time: Future[Long]                                        // Server call
  @client def navigateTo(url: String, push: Boolean): Future[Unit]      // Client method
  @server def counter: Future[Int]                                      // Server method

  name.attach { value =>
    logger.info(s"Name changed: $value")
  }
}