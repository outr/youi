package org.hyperscala.js

class Else(f: => Any) extends Instruction {
  override val output = Some("else {\r\n%s%s}\r\n".format(functionOutput(f), JavaScriptContext().tabs))
}

object Else {
  def apply(f: => Any) = new Else(f)
}