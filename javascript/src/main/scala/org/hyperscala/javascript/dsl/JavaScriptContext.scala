package org.hyperscala.javascript.dsl

import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptContext extends JavaScriptContent {
  implicit def thisContext = this

  private[dsl] var instructions = List.empty[Instruction]

  private[dsl] def +=(instruction: Instruction) = synchronized {
    instructions = instruction :: instructions
  }

  def content = {
    val b = new StringBuilder
    instructions.foreach {
      case i => b.append(s"${i.content};\r\n")
    }
    b.toString()
  }
}

abstract class JavaScript extends JavaScriptContext

trait Instruction extends JavaScriptContent {
  def shouldRender: Boolean
  def context: JavaScriptContext

  context += this
}

case class Var[T](private var value: T)(implicit val context: JavaScriptContext) extends Instruction {
  private[dsl] var name: String = _

  def shouldRender = true

  def declaration = JavaScriptContent.toJS(value)

  def content = if (name == null) declaration else name

  def :=(value: T) = {
    // TODO: add assignment instruction
    this.value = value
  }
}

object Test {
  val js = new JavaScript {
    val guesses = Var(0)
    guesses := 5
  }

  def main(args: Array[String]): Unit = {
    println(js.content)
  }
}