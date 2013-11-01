package org.hyperscala.javascript.dsl2

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object window extends DelayedStatement[HTMLTag] {
  def toStatement(implicit context: JavaScriptContext) = ExistingStatement[HTMLTag]("window")

  def innerWidth(implicit context: JavaScriptContext) = ExistingStatement[Double]("window.innerWidth")
  def innerHeight(implicit context: JavaScriptContext) = ExistingStatement[Double]("window.innerHeight")
}
