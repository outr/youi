package org.hyperscala.javascript

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl2 {
  implicit def string2Statement(s: String)(implicit context: JavaScriptContext = null) = ConstantStatement(s)
  implicit def double2Statement(d: Double)(implicit context: JavaScriptContext = null) = ConstantStatement(d)

  implicit def s2ss(s: Statement[_])(implicit context: JavaScriptContext = null) = ExistingStatement[String](s.toJS)
  implicit def delayed2Statement[T](d: DelayedStatement[T])(implicit context: JavaScriptContext = null) = d.toStatement

  def s(s: String)(implicit context: JavaScriptContext = null) = string2Statement(s)
}