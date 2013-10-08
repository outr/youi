package org.hyperscala.javascript.dsl

import org.powerscala.LocalStack

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JSContext private() {

}

object JSContext {
  private val stack = new LocalStack[JSContext]

  def apply(f: => Unit) = {
    stack.context(new JSContext)(f)
  }
}