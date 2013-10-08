package org.hyperscala.javascript

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def int2NumericStatement(i: Int) = NumericStatement(i.toString)
  implicit def s2Function[R](s: TypedStatement[R]) = new JSFunction0[R] {
    lazy val content =
      s"""
        |function() {
        |   return ${s.content};
        |}
      """.stripMargin
  }
}
