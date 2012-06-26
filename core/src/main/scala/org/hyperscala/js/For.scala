package com.outr.webframework.js

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class For(loop: ForLoop)(f: => Any) extends Instruction {
  override val output = Some("for (%s) {\r\n%s%s}\r\n".format(loop.reference.get, functionOutput(f), JavaScriptContext().tabs))
}

case class ForLoop(ref: String) extends Instruction {
  override val reference = Some(ref)
}