package org.hyperscala.javascript

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl2 {
  implicit def string2Statement(s: String)(implicit context: JavaScriptContext) = ConstantStatement(s)
  implicit def double2Statement(d: Double)(implicit context: JavaScriptContext) = ConstantStatement(d)

  implicit def s2ss(s: Statement[_])(implicit context: JavaScriptContext) = ConstantStatement[String](s.content)
  implicit def delayed2Statement[T](d: DelayedStatement[T])(implicit context: JavaScriptContext) = d.toStatement
}