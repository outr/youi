package io.youi.example

import java.util.concurrent.atomic.AtomicInteger

import io.youi.http.HttpConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
  private val increment = new AtomicInteger(0)

  override def time: Future[Long] = Future(System.currentTimeMillis())
  override def counter: Future[Int] = Future(increment.getAndIncrement())
  override def broadcast(message: String): Future[Unit] = Future(ServerExampleApplication.example.instances.foreach { instance =>
    instance.show(message)
  })
  override def logIn(username: String, password: String): Future[Option[String]] = Future {
    val authorized = username == "user" && password == "password"
    if (authorized) {
      MySession.withConnection(connection) { transaction =>
        transaction.session.username := Some(username)
        Future.successful(transaction)
      }
      None
    } else {
      Some("Invalid username / password combination")
    }
  }

  MySession.withConnection(connection) { transaction =>
    name := transaction.session.username
    Future.successful(transaction)
  }
}