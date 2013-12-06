package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Try[T](f: () => Unit) extends JavaScriptContext {
  f()
  end()

  override def sideEffects = true

  override protected def before(b: StringBuilder, depth: Int) = {
    writeLine(s"try {", b, depth, semicolon = false)
  }

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth + 1, semicolon = false, lineBreak = false)
  }
}

object Try {
  def apply(f: => Unit) = new Try(() => f)
}