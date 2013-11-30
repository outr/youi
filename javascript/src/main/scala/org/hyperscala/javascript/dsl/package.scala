package org.hyperscala.javascript

import scala.language.implicitConversions
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def string2Statement(s: String) = ConstantStatement(s)
  implicit def int2Statement(i: Int) = ConstantStatement(i)
  implicit def double2Statement(d: Double) = ConstantStatement(d)
  implicit def length2Statement(l: Length) = ConstantStatement(l)

  implicit def s2ss(s: Statement[_]) = ExistingStatement[String](s.toJS)
  implicit def delayed2Statement[T](d: DelayedStatement[T]) = d.toStatement

  implicit def statement2Function0[T](s: Statement[T])(implicit manifest: Manifest[T]) = {
    JSFunction0[T](s"return ${s.toJS}")
  }

  def s(s: String) = string2Statement(s)
  def v[T](initialValue: Statement[T] = null) = new Variable[T](initialValue)

  def parseInt[T](s: Statement[T]) = WrappedStatement[Double]("parseInt(", s, ")", sideEffects = false)
}