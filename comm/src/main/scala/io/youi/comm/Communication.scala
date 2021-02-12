package io.youi.comm

import profig._
import reactify._

import cats.effect.IO

//import scala.concurrent.{ExecutionContext, Future}
//
//trait Communication {
//  protected val communicator: Communicator = ??? // TODO: this needs to be defined by implementations
//
//  def method[Send: Writer, Receive: Reader](endpoint: String)
//                                           (implicit ec: ExecutionContext): AsyncMethod[Send, Receive] = new AsyncMethod[Send, Receive] {
//    override def apply(send: Send): Future[Receive] = {
//      val sendJson = JsonUtil.toJson(send)
//      communicator.sendAndReceive(endpoint, sendJson).map { receiveJson =>
//        JsonUtil.fromJson[Receive](receiveJson)
//      }
//    }
//  }
//}
//
//trait Communicator {
//  def sendAndReceive(endpoint: String, json: Json): Future[Json]
//  def createVar[T: ReadWriter](name: String): Var[T]
//  def createChannel[T: ReadWriter](name: String): Channel[T]
//}
//
//trait AsyncMethod[Send, Receive] {
//  def apply(send: Send): Future[Receive]
//}
//
//object Test extends Communication {
//  import scribe.Execution.global
//
//  val savePerson: AsyncMethod[Person, Boolean] = method[Person, Boolean]("savePerson")
//
//  case class Person(name: String, age: Int)
//
//  object Person {
//    implicit val rw: ReadWriter[Person] = macroRW
//  }
//}

object Communication {
  def implementation[Interface](communicator: Communicator, implementation: Interface): Interface with Communication = ???
  def interface[Interface](communicator: Communicator): Interface with Communication = ???
}

trait Communication {
  def communicator: Communicator
}

trait Communicator {
  def method(endpoint: String, send: Json): IO[Json] = {
    scribe.info(s"Should call: $endpoint with $send")
    ???
  }
}

case class TestCommunicator() extends Communicator

trait Test {
  val person: Var[Person] = Var[Person](Person("Default", 21))
  val message: Channel[String] = Channel[String]
  def reverse(s: String): IO[String]
}

class TestImplementation extends Test {
  override def reverse(s: String): IO[String] = IO(s.reverse)
}

object Prototype {
  private val testCommunicator = new TestCommunicator

  val interface: Test with Communication = new Test with Communication {
    override def reverse(s: String): IO[String] = communicator
      .method("reverse", Json.obj("s" -> Json.string(s)))
      .map(_.as[String])

    override def communicator: Communicator = testCommunicator
  }

  val implementation: Test with Communication = new TestImplementation with Communication {
    override def communicator: Communicator = testCommunicator
  }

  def main(args: Array[String]): Unit = {
    val io: IO[String] = interface.reverse("Hello, World!")
    val reversed = io.unsafeRunSync()
    scribe.info(s"Reversed: $reversed")
  }
}

case class Person(name: String, age: Int)

object Person {
  implicit val rw: ReadWriter[Person] = macroRW
}