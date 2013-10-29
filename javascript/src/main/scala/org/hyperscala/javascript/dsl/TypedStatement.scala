package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait TypedStatement[T] extends Statement {
  def +(s: String) = TypedStatement[String](s"($content + '$s')")

  def /(s: TypedStatement[T])(implicit ev: T =:= Double) = TypedStatement[Double](s"($content / ${s.content})")
  def *(s: TypedStatement[T])(implicit ev: T =:= Double) = TypedStatement[Double](s"($content * ${s.content})")
  def +(s: TypedStatement[T])(implicit ev: T =:= Double) = TypedStatement[Double](s"($content + ${s.content})")
  def -(s: TypedStatement[T])(implicit ev: T =:= Double) = TypedStatement[Double](s"($content - ${s.content})")
}

object TypedStatement {
  def apply[T](js: String) = new TypedStatement[T] {
    def content = js
  }
}