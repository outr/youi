package org.hyperscala.numberguess

import org.hyperscala.html._
import org.hyperscala.web.Webpage

import org.hyperscala.javascript.dsl2._

class NumberGuessClientPage extends Webpage {

}

object NumberGuessClientPage {
  def main(args: Array[String]): Unit = {
    val js = new JavaScriptContext {
      val guesses = v[Int]()
      val solution = v[Int]()

      val message = document.getElementById[tag.H1]("message")
      val input = document.getElementById[tag.Input]("input")
      val guessButton = document.getElementById[tag.Button]("guess")
      val resetButton = document.getElementById[tag.Button]("reset")

      val reset = new JSFunction0[Unit]("reset") {
        val test = v[Int](0)
        guesses := 0
      }
    }
    println(js.toJS())
  }
}