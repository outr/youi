package org.hyperscala.javascript.dsl

import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptContent
import scala.collection.immutable.ListMap
import org.powerscala.LocalStack


/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JavaScriptContext extends Statement[JavaScriptContent] {
  private lazy val fields = getClass.fields.filterNot(f => f.name.contains("$"))
  private lazy val variables = ListMap(fields.map(f => f[Any](this) -> f): _*)
  private var statements = List.empty[Statement[_]]
  val parentContext = JavaScriptContext.stack.get()

  JavaScriptContext.stack.push(this)

  def stopPropagation() = {
    ExistingStatement("p1.stopPropagation();", sideEffects = true)
    ExistingStatement("return false;", sideEffects = true)
    end()
  }

  /**
   * Unfortunately because of bugs in getDeclaringClass and DelayedInit this is a necessary evil to be called at the end
   * of all context blocks to allow proper layering.
   */
  def end() = JavaScriptContext.stack.pop(this)

  def variable(v: Any): Option[String] = {
    val variableName = variables.get(v).map(f => f.name)
    if (variableName.isEmpty && parentContext.nonEmpty) {
      parentContext.get.variable(v)
    } else {
      variableName
    }
  }

  protected def hasReturn = false

  protected def before(b: StringBuilder, depth: Int) = {}
  protected def write(b: StringBuilder, depth: Int): Unit = {
    // Assign names to variables
    variables.foreach {
      case (value, field) => value match {
        case s: Statement[_] => s.name = field.name
        case _ => // Ignore
      }
    }

    // Write variables out first
    variables.foreach {
      case (value, field) => value match {
        case s: Statement[_] => // Ignore statements
        case _ => {
          val line = s"var ${field.name} = ${JavaScriptContent.toJS(value)}"
          writeLine(line, b, depth)
        }
      }
    }

    // Write non-variables statements next
    val stmts = statements.reverse
    val last = if (stmts.nonEmpty) stmts.last else null
    stmts.foreach {
      case s => variables.get(s) match {
        case Some(field) => {
          val output = s match {     // Output statement as field
            case v: Variable[_] => v.content
            case c: JavaScriptContext => c.toJS(depth + 1)
            case _ => s.content
          }
          val line = s"var ${field.name} = $output"
          writeLine(line, b, depth)
        }
        case None => {
          val isLast = s eq last
          if ((isLast && hasReturn) || (s.sideEffects && !s.isUsed)) {
            val line = if (isLast && hasReturn) {
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
  protected def writeLine(line: String, b: StringBuilder, depth: Int, semicolon: Boolean = true, lineBreak: Boolean = true) = {
    b.append("".padTo(depth, tab).mkString)
    b.append(line)
    if (semicolon) {
      b.append(';')
    }
    if (lineBreak) {
      b.append("\r\n")
    }
  }

  def toJS(depth: Int = 0) = {
    val b = new StringBuilder

    before(b, depth)
    write(b, depth)
    after(b, depth)

    b.toString()
  }

  def content = toJS()

  def sideEffects = false

  override def toString = content
}

object JavaScriptContext {
  private val stack = new LocalStack[JavaScriptContext]

  def current = stack.get()

  def created(s: Statement[_]) = current match {
    case Some(c) => c.statements = s :: c.statements
    case None => // Not within a context
  }

  def toJS(v: Any) = current match {
    case Some(c) => c.variable(v) match {
      case Some(name) => name
      case None => JavaScriptContent.toJS(v)
    }
    case None => JavaScriptContent.toJS(v)
  }
}