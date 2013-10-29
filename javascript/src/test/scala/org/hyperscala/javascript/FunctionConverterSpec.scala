package org.hyperscala.javascript

import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class FunctionConverterSpec extends WordSpec with Matchers {
  "FunctionConverter with different returns" should {
    "properly return Boolean of true" in {
      val script = FunctionConverter.f0("test", () => {
        val x = true
        x
      })
      cleanScript(script) should equal("function test() {var x = 1;return x;}")
    }
    "properly return Boolean of false" in {
      val script = FunctionConverter.f0("test", () => {
        val x = false
        x
      })
      cleanScript(script) should equal("function test() {var x = 0;return x;}")
    }
    "properly return Int for const range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 4
        x
      })
      cleanScript(script) should equal("function test() {var x = 4;return x;}")
    }
    "properly return Int for higher range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 400
        x
      })
      cleanScript(script) should equal("function test() {var x = 400;return x;}")
    }
    "property return Int without a variable" in {
      val script = FunctionConverter.f0("test", () => {
        5
      })
      cleanScript(script) should equal("function test() {return 5;}")
    }
    "properly return Long for const range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 1L
        x
      })
      cleanScript(script) should equal("function test() {var x = 1;return x;}")
    }
    "properly return Long for higher range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 400L
        x
      })
      cleanScript(script) should equal("function test() {var x = 400;return x;}")
    }
    "properly return Float for const range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 1.0f
        x
      })
      cleanScript(script) should equal("function test() {var x = 1.0;return x;}")
    }
    "properly return Float for higher range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 400.0f
        x
      })
      cleanScript(script) should equal("function test() {var x = 400.0;return x;}")
    }
    "properly return Double for const range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 1.0
        x
      })
      cleanScript(script) should equal("function test() {var x = 1.0;return x;}")
    }
    "properly return Double for higher range" in {
      val script = FunctionConverter.f0("test", () => {
        val x = 400.0
        x
      })
      cleanScript(script) should equal("function test() {var x = 400.0;return x;}")
    }
    "properly return String with a variable" in {
      val script = FunctionConverter.f0("test", () => {
        val x = "Hello"
        x
      })
      cleanScript(script) should equal("function test() {var x = 'Hello';return x;}")
    }
    "properly return String without a variable" in {
      val script = FunctionConverter.f0("test", () => {
        "Hello"
      })
      cleanScript(script) should equal("function test() {return 'Hello';}")
    }
  }
  "FunctionConverter with different args" should {
    "properly invoke with Boolean argument" in {
      val script = FunctionConverter.f1("test", (b: Boolean) => {
        b
      })
      cleanScript(script) should equal("function test(b) {return b;}")
    }
    "properly invoke with Boolean and String argument" in {
      val script = FunctionConverter.f2("test", (b: Boolean, s: String) => {
        b
      })
      cleanScript(script) should equal("function test(b, s) {return b;}")
    }
    "properly invoke with Boolean, String, and Int argument" in {
      val script = FunctionConverter.f3("test", (b: Boolean, s: String, i: Int) => {
        s
      })
      cleanScript(script) should equal("function test(b, s, i) {return s;}")
    }
    "properly invoke with Boolean, String, Int, and Double argument" in {
      val script = FunctionConverter.f4("test", (b: Boolean, s: String, i: Int, d: Double) => {
        i
      })
      cleanScript(script) should equal("function test(b, s, i, d) {return i;}")
    }
  }
  "FunctionConverter with conditionals" should {
    "properly invoke with if equals" in {
      val script = FunctionConverter.f1("test", (i: Int) => {
        if (i == -1) {
          0
        } else {
          i
        }
      })
      cleanScript(script) should equal("function test(i) {if (i == -1) {return 0;} else {return i;}}")
    }
  }

  private def cleanScript(script: String) = script.trim.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "")
}
