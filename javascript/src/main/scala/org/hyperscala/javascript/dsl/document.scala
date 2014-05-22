package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = "document"
  def parent = None

  def thisMatches(t: HTMLTag) = false

  override def quoted = false

  def toStatement = ExistingStatement[HTMLTag]("document")

  def getElementById[T <: HTMLTag](id: Statement[String]) = {
    WrappedStatement[T]("document.getElementById(", id, ")", sideEffects = false)
  }

  def writeln(line: Statement[String]) = {
    WrappedStatement[Unit]("document.writeln(", line, ")", sideEffects = true)
  }

  def duplicate(parent: Option[Selector]) = throw new RuntimeException("document cannot be duplicated")
}