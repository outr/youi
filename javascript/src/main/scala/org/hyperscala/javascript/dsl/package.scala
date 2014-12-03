package org.hyperscala.javascript

import org.hyperscala.javascript.dsl.{JSON, Statement}

import scala.language.implicitConversions
import org.hyperscala.css.attributes.Length
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def boolean2Statement(b: Boolean) = s(b)
  implicit def string2Statement(s: String) = this.s(s)
  implicit def int2Statement(i: Int) = s(i)
  implicit def double2Statement(d: Double) = s(d)
  implicit def length2Statement(l: Length) = s(l)

  implicit def s2ss(s: Statement[_]) = ExistingStatement[String](s.toJS)
  implicit def delayed2Statement[T](d: DelayedStatement[T]) = d.toStatement

  implicit def statement2Function0[T](s: Statement[T])(implicit manifest: Manifest[T]) = {
    JSFunction0[T](s"return ${s.toJS}")
  }
  implicit def statement2JSHTMLTag[T <: HTMLTag](s: Statement[T]) = new JSHTMLTag(s)

  implicit def statement2JavaScriptContent(s: Statement[_]): JavaScriptContent = JavaScriptString(s.content)

  def s[T](t: T) = ConstantStatement[T](t)
  def v[T](initialValue: Statement[T] = null) = new Variable[T](initialValue)

  def parseInt[T](s: Statement[T]) = WrappedStatement[Double]("parseInt(", s, ")", sideEffects = false)

  implicit class JSONStatement(statement: Statement[JSON]) {
    def apply[T](key: String) = MultiStatement[T](sideEffects = false, statement, ".", key)
  }
}