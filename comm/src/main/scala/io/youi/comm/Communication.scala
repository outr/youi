package io.youi.comm

import profig._
import reactify.{Channel, Var}

import scala.concurrent.{ExecutionContext, Future}

trait Communication {
  protected val communicator: Communicator = ??? // TODO: this needs to be defined by implementations

  def method[Send: Writer, Receive: Reader](endpoint: String)
                                           (implicit ec: ExecutionContext): AsyncMethod[Send, Receive] = new AsyncMethod[Send, Receive] {
    override def apply(send: Send): Future[Receive] = {
      val sendJson = JsonUtil.toJson(send)
      communicator.sendAndReceive(endpoint, sendJson).map { receiveJson =>
        JsonUtil.fromJson[Receive](receiveJson)
      }
    }
  }
}

trait Communicator {
  def sendAndReceive(endpoint: String, json: Json): Future[Json]
  def createVar[T: ReadWriter](name: String): Var[T]
  def createChannel[T: ReadWriter](name: String): Channel[T]
}

trait AsyncMethod[Send, Receive] {
  def apply(send: Send): Future[Receive]
}

object Test extends Communication {
  import scribe.Execution.global

  val savePerson: AsyncMethod[Person, Boolean] = method[Person, Boolean]("savePerson")

  case class Person(name: String, age: Int)

  object Person {
    implicit val rw: ReadWriter[Person] = macroRW
  }
}