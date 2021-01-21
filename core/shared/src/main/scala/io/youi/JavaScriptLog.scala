package io.youi

import profig._

case class JavaScriptLog(message: String)

object JavaScriptLog {
  implicit val rw: ReadWriter[JavaScriptLog] = macroRW
}