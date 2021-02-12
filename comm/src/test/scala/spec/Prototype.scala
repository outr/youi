package spec

import cats.effect.IO
import io.youi.comm.{CommunicationInterface, CommunicationImplementation, Communicator, DirectCommunicator}
import profig._
import reactify.{Channel, Var}

trait Test {
  val person: Var[Person] = Var[Person](Person("Default", 21))
  val message: Channel[String] = Channel[String]
  def reverse(s: String): IO[String]
}

object TestImplementation extends Test {
  override def reverse(s: String): IO[String] = IO(s.reverse)
}

object Prototype {
  val implementation: CommunicationImplementation[Test] = CommunicationInterface.implementation[Test](TestImplementation)

  private implicit val testCommunicator: DirectCommunicator[Test] = new DirectCommunicator[Test](implementation)
  val interface: Test with CommunicationInterface = CommunicationInterface.interface[Test]()

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