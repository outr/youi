package org.hyperscala.javascript.dsl2

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JSFunction0[R](name: String)(implicit manifest: Manifest[R]) extends JavaScriptContext {
  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function() {\r\n")
  }

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth - 1, semicolon = false)
  }

  override protected def hasReturn = manifest.runtimeClass != Unit.getClass

  def apply() = ExistingStatement[R](s"$name()", sideEffects = true)
}
