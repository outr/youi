package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object window {
  lazy val innerWidth = NumericStatement("window.innerWidth")
  lazy val innerHeight = NumericStatement("window.innerHeight")
}
