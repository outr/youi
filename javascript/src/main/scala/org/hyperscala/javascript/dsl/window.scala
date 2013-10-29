package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object window {
  lazy val innerWidth = TypedStatement[Double]("window.innerWidth")
  lazy val innerHeight = TypedStatement[Double]("window.innerHeight")
}
