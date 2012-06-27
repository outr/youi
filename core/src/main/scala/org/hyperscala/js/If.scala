package org.hyperscala.js

case class If(condition: Condition)(f: => Any) extends Instruction {
  override val output = Some("if (%s) {\r\n%s%s}\r\n".format(condition.reference.get, functionOutput(f), JavaScriptContext().tabs))
}
