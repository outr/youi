package io.youi.example

import com.outr.reactify.Var
import io.youi.comm.Communication

import scala.concurrent.Future

import com.outr.scribe._

trait ExampleCommunication extends Communication {
  val name: Var[Option[String]] = shared[Option[String]](None)  // Shared Var
  def url: Future[String]                                       // Client call
  def reverse(value: String): Future[String]                    // Server method
  def time: Future[Long]                                        // Server call
  def navigateTo(url: String, push: Boolean): Future[Unit]      // Client method

  name.attach { value =>
    logger.info(s"Name changed: $value")
  }
}