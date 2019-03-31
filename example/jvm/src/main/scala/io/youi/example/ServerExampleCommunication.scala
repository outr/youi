package io.youi.example

import java.util.concurrent.atomic.AtomicInteger

import com.outr.hookup.HookupSupport
import io.youi.http.Connection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication with HookupSupport {
  private val increment = new AtomicInteger(0)

  lazy val connection: Connection = hookup.keyAs[Connection]

  override def time: Future[Long] = Future(System.currentTimeMillis())
  override def counter: Future[Int] = Future(increment.getAndIncrement())
  override def broadcast(message: String): Future[Unit] = Future(ServerExampleApplication.hookup.all.foreach { instance =>
    instance.communication.show(message)
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
    val name = ServerExampleApplication.hookup(connection).name
    name := transaction.session.username
    Future.successful(transaction)
  }
}