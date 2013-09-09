package org.hyperscala.realtime.dsl

import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Var[T](value: T)(implicit manifest: Manifest[T], val context: JavaScript) extends Instruction {
  protected[realtime] var _name: String = _

  def name = _name

  def write(b: StringBuilder) = {
    if (name == null) {
      throw new RuntimeException("Name has not been initialized by the JavaScript context!")
    }
    b.append(s"var $name = ${JavaScriptContent.toJS(value)};\r\n")
  }
}