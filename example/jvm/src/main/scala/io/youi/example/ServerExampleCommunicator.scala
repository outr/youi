package io.youi.example

import com.outr.scribe.Logging
import io.youi.comm.{Communication, ServerWebSocketCommunicator}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

//object ServerExampleCommunicator extends ServerWebSocketCommunication with Logging {
//  override val interface: ExampleInterface = server[ExampleInterface]
//
//  connected.attach { connection =>
//    contextualize(connection) {
//      logger.info("Requesting url from browser...")
//      interface.clientURL().foreach { url =>
//        logger.info(s"Browser is connected on $url")
//      }
//    }
//  }
//
//  def time: Long = System.currentTimeMillis()
//
//  def reverse(text: String): String = text.reverse
//}

trait ServerExampleCommunication extends ExampleCommunication {
//  override val randomId: Long = Random.nextLong()
  override def reverse(value: String): Future[String] = Future(value.reverse)
//  override def time: Future[Long] = Future(System.currentTimeMillis())
}

object ServerExampleCommunication extends ServerWebSocketCommunicator[ServerExampleCommunication] {
  override protected def create(): ServerExampleCommunication = Communication.create[ServerExampleCommunication]
}