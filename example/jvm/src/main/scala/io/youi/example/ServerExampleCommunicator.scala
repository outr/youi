package io.youi.example

import com.outr.scribe.Logging
import io.youi.communicate.ServerWebSocketCommunication

import scala.concurrent.ExecutionContext.Implicits.global

object ServerExampleCommunicator extends ServerWebSocketCommunication with Logging {
  override val interface: ExampleInterface = server[ExampleInterface]

  connected.attach { connection =>
    contextualize(connection) {
      logger.info("Requesting url from browser...")
      interface.clientURL().foreach { url =>
        logger.info(s"Browser is connected on $url")
      }
    }
  }

  def time: Long = System.currentTimeMillis()

  def reverse(text: String): String = text.reverse
}