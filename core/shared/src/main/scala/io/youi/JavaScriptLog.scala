package io.youi

import fabric.rw._

case class JavaScriptLog(message: String)

object JavaScriptLog {
  implicit val rw: ReaderWriter[JavaScriptLog] = ccRW
}