package org.hyperscala.js

import collection.mutable.ListBuffer
import org.hyperscala.{PreFormatted, WebContent}
import xml.Text

trait JavaScriptContent extends WebContent with PreFormatted {
  def toJS: String

  override def preFormatted = Some(toJS)

  def xml = new Text(uuid.toString)
}

case class JavaScriptContentString(toJS: String) extends JavaScriptContent

class JavaScriptContext extends Instruction with JavaScriptContent {
  protected var depth: Int = _
  private val instructions = ListBuffer.empty[Instruction]

  protected[js] def add(i: Instruction) = {
    instructions += i
  }

  def toJS = {
    val b = new StringBuilder
    instructions.map(i => i.output).foreach {
      case Some(output) => b.append("%s%s".format(tabs, output))
      case None => // Nothing to output
    }
    b.toString()
  }

  def tabs = (0 until depth).map(i => '\t').mkString
}

object JavaScriptContext {
  private val stack = new ThreadLocal[List[JavaScriptContext]] {
    override def initialValue() = List.empty[JavaScriptContext]
  }

  def depth = stack.get().size

  def add(context: JavaScriptContext) = {
    context.depth = depth
    val list = stack.get()
    stack.set(context :: list)
  }

  def inContext = stack.get().nonEmpty

  def apply() = stack.get().headOption.getOrElse(null)

  def remove(context: JavaScriptContext) = {
    val list = stack.get()
    stack.set(list.filterNot(c => c == context))
  }

  def contextualize(f: => Any) = {
    val context = new JavaScriptContext()
    add(context)
    f
    remove(context)
    context
  }
}