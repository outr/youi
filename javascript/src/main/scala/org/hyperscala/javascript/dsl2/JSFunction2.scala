package org.hyperscala.javascript.dsl2

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JSFunction2[P1, P2, R](name: String) extends JavaScriptContext {
  val p1 = ExistingStatement[P1]("p1")
  val p2 = ExistingStatement[P1]("p2")

  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function $name(p1, p2) {\r\n")
  }

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth - 1, semicolon = false)
  }

  override def variable(v: Any) = if (v == p1) {
    Some("p1")
  } else if (v == p2) {
    Some("p2")
  } else {
    super.variable(v)
  }
}
