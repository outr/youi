package org.hyperscala.realtime.dsl


/**
 * @author Matt Hicks <matt@outr.com>
 */
object Test {
  def main(args: Array[String]): Unit = {
    val js = new JavaScript {
      var guesses = Var(0)
    }

    val b = new StringBuilder
    js.write(b)
    println(b.toString())
  }
}
