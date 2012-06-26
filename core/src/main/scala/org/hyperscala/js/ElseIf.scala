package com.outr.webframework.js

case class ElseIf(condition: Condition)(f: => Any) extends Instruction {
  override val output = Some("else if (%s) {\r\n%s%s}\r\n".format(condition.reference.get, functionOutput(f), JavaScriptContext().tabs))
}
