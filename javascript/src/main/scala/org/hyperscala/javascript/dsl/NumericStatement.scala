package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait NumericStatement extends TypedStatement[Double] {
  def /(s: NumericStatement) = NumericStatement(s"($content / ${s.content})")
  def *(s: NumericStatement) = NumericStatement(s"($content * ${s.content})")
  def +(s: NumericStatement) = NumericStatement(s"($content + ${s.content})")
  def -(s: NumericStatement) = NumericStatement(s"($content - ${s.content})")
}

object NumericStatement {
  def apply(i: Int): NumericStatement = apply(i.toString)

  def apply(d: Double): NumericStatement = apply(d.toString)

  def apply(js: String) = new NumericStatement {
    def content = js
  }
}