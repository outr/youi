package org.hyperscala.javascript.dsl2

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class JSFunction0[R](name: String) extends JavaScriptContext {
  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function $name() {\r\n")
  }

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth - 1, semicolon = false)
  }

  override protected def hasReturn = true
}
