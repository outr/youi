package org.hyperscala.javascript.dsl2

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object window extends DelayedStatement[HTMLTag] {
  def toStatement = ExistingStatement[HTMLTag]("window")

  def innerWidth = ExistingStatement[Double]("window.innerWidth")
  def innerHeight = ExistingStatement[Double]("window.innerHeight")
}
