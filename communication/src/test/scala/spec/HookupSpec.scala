package spec

import io.circe.Json
import io.youi.communication.{Connection, Hookup, HookupQueue}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.{BeforeAndAfterEach, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class HookupSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {
  "Hookup" should {
    "define an interface" in {
      val connection = new TestConnection
      val queue = connection.queue
      val h = Test.interface(connection)
      h.local.isEmpty should be(true)
      queue.hasNext should be(false)
      val future = h.instance.reverse("Hello, World!")
      queue.hasNext should be(true)
      val request = queue.next().getOrElse(fail())
      request.id should be(1L)
      request.json should be(Json.obj(
        "endpoint" -> Json.fromString("reverse"),
        "params" -> Json.obj(
          "value" -> Json.fromString("Hello, World!")
        )
      ))
      future.isCompleted should be(false)
    }
    "define an implementation" in {
      val connection = new TestConnection
      val queue = connection.queue
      val h = Test.successImplementation(connection)
      h.local.isEmpty should be(false)
      val request = Json.obj(
        "endpoint" -> Json.fromString("reverse"),
        "params" -> Json.obj(
          "value" -> Json.fromString("Hello, World!")
        )
      )
      val future = h.receive(request)
      val result = Await.result(future, 1.second)
      result should be(Json.fromString("!dlroW ,olleH"))
    }
    "complete cycle" in {
      val connection = new TestConnection
      val queue = connection.queue
      val future = Test.fullTest(connection)
      val result = Await.result(future, 1.second)
      result should be("!dlroW ,olleH")
      queue.hasNext should be(false)
    }
    "complete cycle with error" in {
      val connection = new TestConnection
      val queue = connection.queue
      val future = Test.failTest(connection)
      val result = Await.result(future.failed, 1.second)
      result.getMessage should be("Reverse failed!")
      queue.hasNext should be(false)
    }
  }
}

object Test {
  import scribe.Execution.global

  def interface(connection: Connection): Hookup[TestInterface1] = Hookup[TestInterface1](connection)
  def successImplementation(connection: Connection): Hookup[TestInterface1] = {
    Hookup[TestInterface1, Test1](connection)
  }
  def failImplementation(connection: Connection): Hookup[TestInterface1] = {
    Hookup[TestInterface1, Test1Fail](connection)
  }

  def fullTest(connection: Connection): Future[String] = {
    val interface = Test.interface(connection)
    val implementation = Test.successImplementation(connection)
    val future = interface.instance.reverse("Hello, World!")
    val request = connection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
    implementation.receive(request.json).map { json =>
      request.success(json)
    }
    future
  }

  def failTest(connection: Connection): Future[String] = {
    val interface = Test.interface(connection)
    val implementation = Test.failImplementation(connection)
    val future = interface.instance.reverse("Hello, World!")
    val request = connection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
    implementation.receive(request.json).failed.map { t =>
      request.failure(t)
    }
    future
  }
}

class TestConnection extends Connection {
}

trait TestInterface1 {
  def reverse(value: String): Future[String]

  def createUser(name: String, age: Int, city: Option[String]): Future[User]
}

class Test1 extends TestInterface1 with Hookup[TestInterface1] {
  override def reverse(value: String): Future[String] = Future.successful(value.reverse)

  override def createUser(name: String, age: Int, city: Option[String]): Future[User] = Future.successful {
    User(name, age, city)
  }
}

class Test1Fail extends TestInterface1 {
  override def reverse(value: String): Future[String] = throw new RuntimeException("Reverse failed!")

  override def createUser(name: String, age: Int, city: Option[String]): Future[User] = throw new RuntimeException("Create User failed!")
}

case class User(name: String, age: Int, city: Option[String])