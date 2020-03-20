// TODO: re-enable once the linker doesn't flip out anymore
/*
package spec

import io.circe.Json
import io.youi.communication.{Connection, Hookup, Message, MessageType}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scribe.Execution.global

class HookupSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {
  "Hookup" should {
    "define an interface" in {
      val connection = TestInterfaceConnection
      val queue = connection.queue
      val h = connection.i
      h.local.isEmpty should be(true)
      queue.hasNext() should be(false)
      val future = h.instance.reverse("Hello, World!")
      queue.hasNext() should be(true)
      val request = queue.next().getOrElse(fail())
      request.request.id should be(1L)
      request.request.params.get should be(Json.obj("value" -> Json.fromString("Hello, World!")))
      future.isCompleted should be(false)
    }
    "define an implementation" in {
      val connection = TestImplementationConnection
      val h = connection.i
      h.local.isEmpty should be(false)
      val future = h.receive(Message.invoke("i", "reverse", Json.obj("value"  -> Json.fromString("Hello, World!"))))
      val result = Await.result(future, 1.second)
      result.returnValue.get should be(Json.fromString("!dlroW ,olleH"))
    }
    "complete cycle" in {
      val future = Test.fullTest()
      val result = Await.result(future, 1.second)
      result should be("!dlroW ,olleH")
    }
    "complete cycle with error" in {
      val interface = TestInterfaceConnection.i
      val implementation = TestFailConnection.i
      interface.reverse("Hello, World!")
      val request = TestInterfaceConnection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
      val result = Await.result(implementation.receive(request.request), 1.second)
      result.`type` should be(MessageType.Error)
    }
    "use Connection to createUser" in {
      TestInterfaceConnection.queue.clear()
      TestInterfaceConnection.queue.hasNext() should be(false)
      TestInterfaceConnection.queue.hasRunning() should be(false)
      val future = TestInterfaceConnection.i.createUser("John Doe", 21, Some("Somewhere"))
      TestInterfaceConnection.queue.hasNext() should be(true)
      TestInterfaceConnection.queue.hasRunning() should be(false)
      val request = TestInterfaceConnection.queue.next().getOrElse(fail())
      TestImplementationConnection.receive(request.request).map { response =>
        TestInterfaceConnection.queue.hasNext() should be(false)
        TestInterfaceConnection.queue.hasRunning() should be(true)
        TestInterfaceConnection.queue.success(response)
      }
      val result = Await.result(future, 1.second)
      result should be(User("John Doe", 21, Some("Somewhere")))
      TestInterfaceConnection.queue.hasNext() should be(false)
      TestInterfaceConnection.queue.hasRunning() should be(false)
    }
    "use Connection to time" in {
      TestInterfaceConnection.queue.clear()
      TestInterfaceConnection.queue.hasNext() should be(false)
      TestInterfaceConnection.queue.hasRunning() should be(false)
      val time = System.currentTimeMillis()
      val future = TestInterfaceConnection.i.time
      TestInterfaceConnection.queue.hasNext() should be(true)
      TestInterfaceConnection.queue.hasRunning() should be(false)
      val request = TestInterfaceConnection.queue.next().getOrElse(fail())
      TestImplementationConnection.receive(request.request).map { response =>
        TestInterfaceConnection.queue.hasNext() should be(false)
        TestInterfaceConnection.queue.hasRunning() should be(true)
        TestInterfaceConnection.queue.success(response)
      }
      val result = Await.result(future, 1.second)
      result should be >= time
      TestInterfaceConnection.queue.hasNext() should be(false)
      TestInterfaceConnection.queue.hasRunning() should be(false)
    }
  }
}

object Test {
  import scribe.Execution.global

  def fullTest(): Future[String] = {
    val interface = TestInterfaceConnection.i
    val implementation = TestImplementationConnection.i
    val future = interface.reverse("Hello, World!")
    val request = TestInterfaceConnection.queue.next().getOrElse(throw new RuntimeException("Request not in queue"))
    implementation.receive(request.request).map { response =>
      request.success(response)
    }
    future
  }
}

object TestInterfaceConnection extends Connection {
  val i: TestInterface1 with Hookup[TestInterface1] = interface[TestInterface1]
}

object TestImplementationConnection extends Connection {
  val i: TestInterface1 with Hookup[TestInterface1] = implementation[TestInterface1, Test1]
}

object TestFailConnection extends Connection {
  val i: TestInterface1 with Hookup[TestInterface1] = implementation[TestInterface1, Test1Fail]
}

trait TestInterface1 {
  def time: Future[Long]

  def reverse(value: String): Future[String]

  def createUser(name: String, age: Int, city: Option[String]): Future[User]
}

class Test1 extends TestInterface1 with Hookup[TestInterface1] {
  override def time: Future[Long] = Future.successful(System.currentTimeMillis())

  override def reverse(value: String): Future[String] = Future.successful(value.reverse)

  override def createUser(name: String, age: Int, city: Option[String]): Future[User] = Future.successful {
    User(name, age, city)
  }
}

class Test1Fail extends TestInterface1 {
  override def time: Future[Long] = throw new RuntimeException("Time failed!")

  override def reverse(value: String): Future[String] = throw new RuntimeException("Reverse failed!")

  override def createUser(name: String, age: Int, city: Option[String]): Future[User] = throw new RuntimeException("Create User failed!")
}

case class User(name: String, age: Int, city: Option[String])*/
