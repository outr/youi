package org.hyperscala.javascript.dsl2

import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptContent
import scala.collection.immutable.ListMap


/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptContext {
  implicit val jsContext = this

  private lazy val fields = getClass.fields.filterNot(f => f.name.contains("$") || f.name == "jsContext")
  private lazy val variables = ListMap(fields.map(f => f[Any](this) -> f): _*)
  private var statements = List.empty[Statement[_]]

  def variable(v: Any): Option[String] = {
    variables.get(v).map(f => f.name)
  }

  protected def before(b: StringBuilder, depth: Int) = {}
  protected def write(b: StringBuilder, depth: Int): Unit = {
    // Write variables out first
    variables.foreach {
      case (value, field) => {
        val output = value match {
          case v: Variable[_] => {
            v.name = field.name
            v.content
          }
          case s: Statement[_] => s.content
          case c: JavaScriptContext => c.toJS(depth + 1)
          case _ => JavaScriptContent.toJS(value)
        }
        val line = s"var ${field.name} = $output"
        writeLine(line, b, depth)
      }
    }

    // Write non-variables statements next
    val stmts = statements.reverse
    val last = if (stmts.nonEmpty) stmts.last else null
    stmts.foreach {
      case s => variables.get(s) match {
        case Some(field) => {     // Output statement as field

        }
        case None => {
          val isLast = s eq last
          if (isLast || s.sideEffects) {
            val line = if (isLast) {
              s"return ${s.toJS}"
            } else {
              s.toJS
            }
            writeLine(line, b, depth)
          }
        }
      }
    }
  }
  protected def after(b: StringBuilder, depth: Int) = {}
  protected def tab = "\t"
  protected def writeLine(line: String, b: StringBuilder, depth: Int, semicolon: Boolean = true) = {
    b.append("".padTo(depth, tab).mkString)
    b.append(line)
    // TODO: add semicolon support back in for proper JS generation
//    if (semicolon) {
//      b.append(';')
//    }
    b.append("\r\n")
  }

  def toJS(depth: Int = 0) = {
    val b = new StringBuilder

    before(b, depth)
    write(b, depth)
    after(b, depth)

    b.toString()
  }
}

object JavaScriptContext {
  def created(s: Statement[_]) = if (s.context != null) {
    s.context.statements = s :: s.context.statements
  }

  def toJS(v: Any, context: JavaScriptContext) = if (context != null) {
    context.variable(v) match {
      case Some(name) => name
      case None => JavaScriptContent.toJS(v)
    }
  } else {
    JavaScriptContent.toJS(v)
  }
}