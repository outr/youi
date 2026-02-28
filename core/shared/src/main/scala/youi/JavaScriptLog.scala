package youi

import fabric.rw._

case class JavaScriptLog(message: String)

object JavaScriptLog {
  implicit val rw: RW[JavaScriptLog] = RW.gen
}