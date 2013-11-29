package org.hyperscala.javascript.dsl2

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JSFunction1[P1, R](name: String)(implicit manifest: Manifest[R]) extends JavaScriptContext {
  val p1 = ExistingStatement[P1]("p1")

  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function(p1) {\r\n")
  }

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth - 1, semicolon = false)
  }

  override def variable(v: Any) = if (v == p1) {
    Some("p1")
  } else {
    super.variable(v)
  }

  override protected def hasReturn = manifest.runtimeClass != Unit.getClass

  def apply(p1: Statement[P1]) = WrappedStatement[R](s"$name(", p1, ")", sideEffects = true)
}
