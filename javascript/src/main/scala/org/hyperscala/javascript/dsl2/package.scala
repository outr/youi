package org.hyperscala.javascript

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl2 {
  implicit def double2Statement(d: Double)(implicit context: JavaScriptContext) = ConstantStatement(d)
}
