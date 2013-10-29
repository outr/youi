package org.hyperscala.javascript.dsl

import org.powerscala.LocalStack
import org.powerscala.reflect._
import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScript {
  JavaScript.stack.push(this)

  private lazy val fields = getClass.fields.filterNot(f => f.name.contains("$"))
  private var statements = List.empty[Statement]

  def toJS() = {
    val b = new StringBuilder
    val variables = fields.map(f => f[Any](this) -> f).toMap

    // Write variables out first
    variables.foreach {
      case (value, field) => b.append(s"var ${field.name} = ${JavaScriptContent.toJS(value)};\r\n")
    }

    // Write non-variables statements next
    val stmts = statements.reverse
    val last = stmts.last
    stmts.foreach {
      case s => variables.get(s) match {
        case Some(field) => // Already output as variable, no need to write it again
        case None => {
          // TODO: support extraction of variables used in statements
          if (s eq last) {
            b.append("return ")
          }
          b.append(s"${s.content};\r\n")
        }
      }
    }

    b.toString()
  }

  def close() = {
    // TODO: terminate
    JavaScript.stack.pop()      // TODO: make sure this is the one popped
  }
}

object JavaScript {
  private val stack = new LocalStack[JavaScript]

  def statementCreated(s: Statement) = {
    stack.get() match {
      case Some(context) => context.statements = s :: context.statements
      case None => // No context
    }
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val js = new JavaScript {
      val a = 5.3
      val b = Math.floor(a)
      b - 1
    }
    js.close()
    println(js.toJS())

    /*
    Should be:

    var a = 5.3;
    var b = Math.floor(a)
    return b - 1;
     */
  }
}