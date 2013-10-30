package org.hyperscala.javascript.dsl2

import org.powerscala.LocalStack
import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptContext {
  JavaScriptContext.stack.push(this)

  private lazy val fields = getClass.fields.filterNot(f => f.name.contains("$"))
  private lazy val variables = fields.map(f => f[Any](this) -> f).toMap
  private var statements = List.empty[Statement[_]]

  def variable(v: Any): Option[String] = {
    variables.get(v).map(f => f.name)
  }

  protected def before(b: StringBuilder) = {}
  protected def after(b: StringBuilder) = {}

  def toJS = {
    val b = new StringBuilder

    before(b)

    // Write variables out first
    variables.foreach {
      case (value, field) => {
        val output = value match {
          case s: Statement[_] => s.content
          case _ => JavaScriptContent.toJS(value)
        }
        b.append(s"var ${field.name} = $output;\r\n")
      }
    }

    // Write non-variables statements next
    val stmts = statements.reverse
    val last = stmts.last
    stmts.foreach {
      case s => variables.get(s) match {
        case Some(field) => // Already output as variable, no need to write it again
        case None => {
          val isLast = s eq last
          if (isLast || s.sideEffects) {
            if (isLast) {
              b.append("return ")
            }
            b.append(s"${s.toJS};\r\n")
          }
        }
      }
    }

    after(b)

    b.toString()
  }

  /**
   * Finish should be invoked after the last statement in the context.
   */
  def finish(): Unit = JavaScriptContext.stack.pop(this)
}

object JavaScriptContext {
  private val stack = new LocalStack[JavaScriptContext]

  def created(s: Statement[_]) = {
    val context = stack.get()
    context match {
      case Some(c) => c.statements = s :: c.statements
      case None => // Not in a context
    }
    context
  }

  def toJS(v: Any) = stack.get() match {
    case Some(context) => context.variable(v) match {
      case Some(name) => name
      case None => JavaScriptContent.toJS(v)
    }
    case None => JavaScriptContent.toJS(v)
  }
}