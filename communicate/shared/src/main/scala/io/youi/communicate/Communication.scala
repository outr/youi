package io.youi.communicate

import pl.metastack.metarx.{Channel, ReadChannel}

import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox
import scala.language.experimental.macros

trait Communication {
  protected def register[T]: Communicator[T] = macro Communication.register[T]
}

@compileTimeOnly("Enable macro support")
object Communication {
  def register[T](c: blackbox.Context)(implicit t: c.WeakTypeTag[T]): c.Expr[Communicator[T]] = {
    import c.universe._

    c.Expr[Communicator[T]](
      q"""
         new io.youi.communicate.Communicator[$t] {
            override val id: Int = io.youi.communicate.Communicator.nextId
            override protected def communication = ${c.prefix.tree}
            override protected def read(json: String): $t = upickle.default.read[$t](json)
            override protected def write(t: $t): String = upickle.default.write[$t](t)
         }
       """)
  }
}

trait Communicator[T] {
  val id: Int
  val channel = Channel[Message[T]]()

  protected def communication: Communication
  protected def read(json: String): T

  protected def write(t: T): String
}

object Communicator {
  private var increment = 0
  def nextId: Int = synchronized {
    increment += 1
    increment
  }
}

case class Message[T](connection: Connection, value: T)

trait Connection