package io.youi.example

import java.util.concurrent.atomic.AtomicInteger

import io.youi.http.HttpConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
  private val increment = new AtomicInteger(0)

  override def time: Future[Long] = Future(System.currentTimeMillis())
  override def counter: Future[Int] = Future(increment.getAndIncrement())
  override def broadcast(message: String): Future[Unit] = Future(ServerExampleApplication.comm.instances.foreach { instance =>
    instance.show(message)
  })
  override def logIn(username: String, password: String): Future[Option[String]] = Future {
    val authorized = username == "user" && password == "password"
    if (authorized) {
      MySession(connection.store[HttpConnection]("httpConnection")).username := Some(username)
      None
    } else {
      Some("Invalid username / password combination")
    }
  }

  name := MySession(connection.store[HttpConnection]("httpConnection")).username
}