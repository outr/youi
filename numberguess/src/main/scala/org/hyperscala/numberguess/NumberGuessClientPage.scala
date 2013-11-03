package org.hyperscala.numberguess

import org.hyperscala.web.Webpage

import org.hyperscala.javascript.dsl2._

class NumberGuessClientPage extends Webpage {

}

object NumberGuessClientPage {
  def main(args: Array[String]): Unit = {
    val js = new JavaScriptContext {
      val guesses = v[Int](0)
      guesses := 5
      guesses
    }
    println(js.toJS())
  }
}