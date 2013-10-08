package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait TypedStatement[T] extends Statement {
  def +(s: String) = TypedStatement[String](s"($content + '$s')")
}

object TypedStatement {
  def apply[T](js: String) = new TypedStatement[T] {
    def content = js
  }
}