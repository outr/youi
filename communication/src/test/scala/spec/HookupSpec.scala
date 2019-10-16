package spec

import io.circe.Json
import io.youi.communication.{Connection, Hookup}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.{BeforeAndAfterEach, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scribe.Execution.global

class HookupSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {
  "Hookup" should {
    "define an interface" in {
      val connection = TestInterfaceConnection
      val queue = connection.queue
      val h = connection.interface
      h.local.isEmpty should be(true)
      queue.hasNext should be(false)
      val future = h.instance.reverse("Hello, World!")
      queue.hasNext should be(true)
      val request = queue.next().getOrElse(fail())
      request.id should be(1L)
      request.json should be(Json.obj(
        "endpoint" -> Json.fromString("i.reverse"),
        "params" -> Json.obj(
          "value" -> Json.fromString("Hello, World!")
        )
      ))
      future.isCompleted should be(false)
    }
    "define an implementation" in {
      val connection = TestImplementationConnection
      val queue = connection.queue
      val h = connection.interface
      h.local.isEmpty should be(false)
      val request = Json.obj(
        "endpoint" -> Json.fromString("i.reverse"),
        "params" -> Json.obj(
          "value" -> Json.fromString("Hello, World!")
        )
      )
      val future = h.receive(request)
      val result = Await.result(future, 1.second)
      result should be(Json.fromString("!dlroW ,olleH"))
    }
    "complete cycle" in {
      val future = Test.fullTest()
      val result = Await.result(future, 1.second)
      result should be("!dlroW ,olleH")
    }
    "complete cycle with error" in {
      val future = Test.failTest()
      val result = Await.result(future.failed, 1.second)
      result.getMessage should be("Reverse failed!")
    }
  }
}

object Test {
  import scribe.Execution.global

  def fullTest(): Future[String] = {
    val interface = TestInterfaceConnection.interface
    val implementation = TestImplementationConnection.interface
    val future = interface.reverse("Hello, World!")
    val request = TestInterfaceConnection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
    implementation.receive(request.json).map { json =>
      request.success(json)
    }
    future
  }

  def failTest(): Future[String] = {
    val interface = TestInterfaceConnection.interface
    val implementation = TestFailConnection.interface
    val future = interface.reverse("Hello, World!")
    val request = TestInterfaceConnection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
    implementation.receive(request.json).failed.map { t =>
      request.failure(t)
    }
    future
  }
}

object TestInterfaceConnection extends Connection {
  val interface: TestInterface1 with Hookup[TestInterface1] = apply[TestInterface1]("i")
}

object TestImplementationConnection extends Connection {
  val interface: TestInterface1 with Hookup[TestInterface1] = apply[TestInterface1, Test1]("i")
}

object TestFailConnection extends Connection {
  val interface: TestInterface1 with Hookup[TestInterface1] = apply[TestInterface1, Test1Fail]("i")
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