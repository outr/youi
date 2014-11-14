package org.hyperscala.javascript

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JavaScriptContextSpec extends WordSpec with Matchers {
  "JavaScriptContext" when {
    "used with a JSFunction0" should {
      "handle a basic command with side-effects" in {
        val js = new JSFunction0[Unit]() {
          document.writeln("Hello World!")
        }
        val expected = "function() { document.writeln('Hello World!'); }"
        clean(js.content) should equal(expected)
      }
      "handle variables with return" in {
        val js = new JSFunction0[Double]() {
          val a = v(5.3)
          a := 6.0
          val b = v(Math.floor(a))
          b += 2.0
          end()
        }
        val expected = "function() { var a = 5.3; a = 6.0; var b = Math.floor(a); return b += 2.0; }"
        clean(js.content) should equal(expected)
      }
      "create a complex function" in {
        val js = new JSFunction0[Unit] {
//          val input = window.parent.document.getElementById('myElement')
//          val visual2Internal = JSObject(Map("one" -> "1", "two" -> "2", "three" -> "3"))
//          val internal2Visual = JSObject(Map("1" -> "one", "2" -> "two", "3" -> "three"))

        }
      }
    }
    "used with a JSFunction2" should {
      "support layering of functions" in {
        val f1 = new JSFunction2[Double, Double, Double] {
          val f2 = new JSFunction2[String, String, String] {
            p1 + p2
            end()
          }

          document.writeln(s("Hello World! ") + window.innerWidth + "x" + window.innerHeight)

          p1 * p2
          end()
        }
        val expected = "function(p1, p2) { var f2 = function(p1, p2) { return p1 + p2; }; document.writeln('Hello World! ' + window.innerWidth + 'x' + window.innerHeight); return (p1 * p2); }"
        clean(f1.content) should equal(expected)
      }
    }
  }

  private def clean(s: String) = s.replaceAll("""\s+""", " ")
}
