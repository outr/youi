package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends DelayedStatement[HTMLTag] {
  def toStatement = ExistingStatement[HTMLTag]("document")

  def getElementById[T <: HTMLTag](id: Statement[String]) = {
    WrappedStatement[T]("document.getElementById(", id, ")", sideEffects = false)
  }

  def writeln(line: Statement[String]) = {
    WrappedStatement[Unit]("document.writeln(", line, ")", sideEffects = true)
  }
}