package org.hyperscala.javascript.dsl2

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends DelayedStatement[HTMLTag] {
  def toStatement(implicit context: JavaScriptContext) = ExistingStatement[HTMLTag]("document")

  def getElementById[T <: HTMLTag](id: Statement[String])(implicit context: JavaScriptContext) = {
    WrappedStatement[T]("document.getElementById(", id, ")", sideEffects = false)
  }

  def writeln(line: Statement[String])(implicit context: JavaScriptContext) = {
    WrappedStatement[Unit]("document.writeln(", line, ")", sideEffects = true)
  }
}