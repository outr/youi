package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
object body extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = "body"
  def parent = None

  def thisMatches(t: HTMLTag) = false

  override def quoted = false

  def toStatement = ExistingStatement[HTMLTag]("body")

  def duplicate(parent: Option[Selector]) = throw new RuntimeException("body cannot be duplicated")
}