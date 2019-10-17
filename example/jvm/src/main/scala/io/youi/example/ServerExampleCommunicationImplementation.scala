package io.youi.example

import java.util.concurrent.atomic.AtomicInteger

import io.youi.communication.Hookup

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunicationImplementation extends ServerExampleCommunication with Hookup[ServerExampleCommunication] {
  private val increment = new AtomicInteger(0)

  override def reverse(value: String): Future[String] = Future.successful(value.reverse)

  override def time: Future[Long] = Future(System.currentTimeMillis())
  override def counter: Future[Int] = Future(increment.getAndIncrement())
  override def broadcast(message: String): Future[Unit] = Future {
    ServerExampleApplication.connectivity.connections.foreach { connectivity =>
      connectivity.connection.client.show(message)
    }
  }
  override def logIn(username: String, password: String): Future[Option[String]] = Future {
    val authorized = username == "user" && password == "password"
    if (authorized) {
//
//      MySession.webWebSocketListener(connection) { transaction =>
//        transaction.session.username @= Some(username)
//        Future.successful(transaction)
//      }
      scribe.info("Not implemented!")
      None
    } else {
      Some("Invalid username / password combination")
    }
  }

  /*MySession.withConnection(connection) { transaction =>
    val hookup = ServerExampleApplication.hookup(connection)
    val name = hookup.name
    name := transaction.session.username
    Future.successful(transaction)
  }*/
}