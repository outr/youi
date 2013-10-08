package org.hyperscala.jquery.dsl

import org.hyperscala.javascript.dsl._
import org.hyperscala.javascript.JSFunction0

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Test {
  def main(args: Array[String]): Unit = {
    val t: JSFunction0[String] = ((window.innerWidth - $("#myDiv").width()) / 2) + "px"
    println(t.content)
  }
}