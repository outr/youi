package org.hyperscala.js

import org.hyperscala.tags._
import org.hyperscala._

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object JavaScript {
  def apply(f: => Any) = JavaScriptContext.contextualize(f)

  def apply(s: String) = JavaScriptContentString(s)

  def main(args: scala.Array[String]): Unit = {
    val js = JavaScript {
//      val x = Variable(5, "x")
//      val y = Variable(6, "y")
//      val z = Variable("Testing", "z")
//      y := 10
//      val entries = Array(List(Constant(1), x, Constant(3), Constant(4)), "entries")
//      entries(4) = 5
//      If (x Equals y) {
//        val a = Variable(1, "a")
//        entries(6) = 0
//        If (a Equals x) {
//          a := 2
//        }
//      }
//      ElseIf (x <= y) {
//        x := 0
//      }
//      ElseIf (x NotEquals 5) {
//        z := "Wahoo!"
//      }
//      Else {
//        z := "Boohoo!"
//      }
//
//      For (x in entries) {
//        alert(z)
//      }
//
//      JsFunction.f1("testFunction")((v: TypedVar[Int]) => {
//        alert(Var.format("The value is: %s, %s, %s", v, x, y))
//      })
//
//      val input = Variable.content(document.getElementById[Input]("input"), "input")
//      input.value := z
//
//      val actual = ExistingVariable[Int]("document.actualNumber")
//      val random = V(Math.floor((Math.random() * 100.0) + 1.0), "random")
//      actual := random

      val heading = Variable.content(document.getElementById[H5]("heading"), "heading")
      heading.contents.clear()
      heading.contents += new Div {
        contents += new B(content = "Hello")
        contents += " World"
      }
    }
    println(js.toJS)
  }

  def outputConstant(value: Any) = value match {
    case null => "null"
    case s: String => "'%s'".format(s)
    case v => v.toString
  }
}