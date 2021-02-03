package io.youi.comm

import cats.effect.IO
import profig._
import reactify.{Channel, Var}

import java.lang.reflect.{Method, Modifier}
import scala.concurrent.{ExecutionContext, Future}

trait Communication {
  /*protected val communicator: Communicator = ??? // TODO: this needs to be defined by implementations

  def method[Send: Writer, Receive: Reader](endpoint: String)
                                           (implicit ec: ExecutionContext): AsyncMethod[Send, Receive] = new AsyncMethod[Send, Receive] {
    override def apply(send: Send): Future[Receive] = {
      val sendJson = JsonUtil.toJson(send)
      communicator.sendAndReceive(endpoint, sendJson).map { receiveJson =>
        JsonUtil.fromJson[Receive](receiveJson)
      }
    }
  }*/

  protected def method(endpoint: String, parameters: (String, Json)*): IO[Json]
}

trait ReflectiveCommunication[Interface] extends Communication {
  protected def implementation: Interface

  private val methods: Map[String, Method] = implementation
    .getClass
    .getDeclaredMethods
    .toList
    .filter(m => Modifier.isPublic(m.getModifiers))
    .filterNot(_.getName.startsWith("$"))
    .map { m =>
      m.getName -> m
    }
    .toMap

  override def method(name: String, parameters: (String, Json)*): IO[Json] = methods.get(name) match {
    case Some(m) =>
  }
}

/*trait Communicator {
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
}*/

trait Simple {
  def reverse(value: String): IO[String]
}

object SimpleImplementation extends Simple {
  override def reverse(value: String): IO[String] = IO(value.reverse)
}

object SimpleRemote extends Simple with ReflectiveCommunication[Simple] {
  override protected def implementation: Simple = SimpleImplementation

  override def reverse(value: String): IO[String] = method("reverse", "value" -> Json.string(value)).map(_.as[String])
}

object Test {
  def main(args: Array[String]): Unit = {
    SimpleRemote
  }
}