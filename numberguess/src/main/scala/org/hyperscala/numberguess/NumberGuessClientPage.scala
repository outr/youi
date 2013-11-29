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
      val solution = v[Double]()

      val message = document.getElementById[tag.H1]("message")
      val input = document.getElementById[tag.Input]("input")
      val guessButton = document.getElementById[tag.Button]("guess")
      val resetButton = document.getElementById[tag.Button]("reset")

      val generateSolution = new JSFunction0[Double]("generateSolution") {
        Math.floor((Math.random() * 100.0) + 1.0)
      }

      val setMessage = new JSFunction1[String, Unit]("setMessage") {
//        message.innerHTML := a
      }

      val reset = new JSFunction0[Unit]("reset") {
        guesses := 0
        solution := generateSolution()
        setMessage("Guess a number between 0 and 100.")
//        input.value := ""
//        guessButton.disabled := false
//        resetButton.disabled := true
      }

      val attempt = new JSFunction0[Unit]("attempt") {

      }

      reset()
    }
    println(js.toJS())
  }
}