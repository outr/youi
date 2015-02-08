package org.hyperscala.javascript

import org.hyperscala.javascript.dsl.{JSON, Statement}

import scala.language.implicitConversions
import org.hyperscala.css.attributes.Length
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def boolean2Statement(b: Boolean): Statement[Boolean] = s(b)
  implicit def string2Statement(s: String): Statement[String] = this.s(s)
  implicit def int2Statement(i: Int): Statement[Int] = s(i)
  implicit def double2Statement(d: Double): Statement[Double] = s(d)
  implicit def length2Statement(l: Length): Statement[Length] = s(l)

  implicit def js2Statement[T](js: JavaScriptContent): Statement[T] = ExistingStatement[T](js.content)
  implicit def s2js(s: String): JavaScriptContent = JavaScriptString(s)

  implicit def s2ss(s: Statement[_]): Statement[String] = ExistingStatement[String](s.toJS)
  implicit def delayed2Statement[T](d: DelayedStatement[T]): Statement[T] = d.toStatement

  implicit def statement2Function0[T](s: Statement[T])(implicit manifest: Manifest[T]): JSFunction0[T] = {
    JSFunction0[T](s"return ${s.toJS}")
  }
  implicit def statement2JSHTMLTag[T <: HTMLTag](s: Statement[T]): JSHTMLTag[T] = new JSHTMLTag(s)

  implicit def statement2JavaScriptContent(s: Statement[_]): JavaScriptContent = JavaScriptString(s.content)

  def s[T](t: T) = ConstantStatement[T](t)
  def v[T](initialValue: Statement[T] = null) = new Variable[T](initialValue)

  def parseInt[T](s: Statement[T]) = WrappedStatement[Double]("parseInt(", s, ")", sideEffects = false)

  def isNaN(s: Statement[String]) = WrappedStatement[Boolean]("isNaN(", s, ")", sideEffects = false)

  implicit class JSONStatement(statement: Statement[JSON]) {
    def apply[T](key: String) = MultiStatement[T](sideEffects = false, statement, ".", key)
  }
}