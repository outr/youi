package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object body extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = "body"
  def parentSelector = None

  def thisMatches(t: HTMLTag) = false

  override def quoted = true

  def toStatement = ExistingStatement[HTMLTag]("body")

  def duplicate(parent: Option[Selector]) = throw new RuntimeException("body cannot be duplicated")
}