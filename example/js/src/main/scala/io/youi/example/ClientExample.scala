package io.youi.example

import com.outr.scribe.Logging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSApp
import scala.util.{Failure, Success}

object ClientExample extends JSApp with Logging {
  override def main(): Unit = {
    println("Hello World!")
    ClientExampleCommunicator.connected.attach { c =>
      logger.info(s"Connected: $c")
      ClientExampleCommunicator.interface.reverse("Testing!").onComplete {
        case Success(reversed) => logger.info(s"Reversed: $reversed")
        case Failure(t) => logger.error(t)
      }
      logger.info("Sent for reversing!!!")
    }
    ClientExampleCommunicator.connect()
  }
}