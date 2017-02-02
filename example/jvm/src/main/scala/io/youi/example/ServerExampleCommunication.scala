package io.youi.example

import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
  private val increment = new AtomicInteger(0)

  override def reverse(value: String): Future[String] = Future(value.reverse)
  override def time: Future[Long] = Future(System.currentTimeMillis())
  override def counter: Future[Int] = Future(increment.getAndIncrement())
  override def broadcast(message: String): Future[Unit] = Future(ServerExampleApplication.comm.instances.foreach { instance =>
    instance.show(message)
  })
  override def logIn(username: String, password: String): Future[Option[String]] = Future {
    val authorized = username == "user" && password == "password"
    if (authorized) {
      println("Before...")
      MySession(ServerExampleApplication).username := Some(username)
      println("...After")
      None
    } else {
      Some("Invalid username / password combination")
    }
  }

  name := MySession(ServerExampleApplication).username
}