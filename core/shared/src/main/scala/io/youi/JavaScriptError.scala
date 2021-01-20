package io.youi

import profig._

case class JavaScriptError(message: String,
                           source: String,
                           fileName: String,
                           jsPosition: JavaScriptPosition,
                           position: JavaScriptPosition,
                           url: String,
                           cause: Option[JavaScriptCause]) {
  override def toString: String =
    s"""
       |Error {
       |  message: $message
       |  jsTrace: $source:${jsPosition.line}:${jsPosition.column}
       |  trace: $fileName:${position.line}:${position.column}
       |  url: $url
       |  cause: $cause
       |}
     """.stripMargin.trim
}

object JavaScriptError {
  implicit val rw: ReadWriter[JavaScriptError] = macroRW
}

case class JavaScriptCause(message: String,
                           trace: List[JavaScriptTrace],
                           cause: Option[JavaScriptCause]) {
  override def toString: String =
    s"""
       |Cause {
       |    message: $message
       |    trace:\n${trace.map(s => s"\t$s").mkString("\n")}
       |    cause: $cause
       |  }
     """.stripMargin.trim
}

object JavaScriptCause {
  implicit val rw: ReadWriter[JavaScriptCause] = macroRW
}

case class JavaScriptTrace(className: String,
                           methodName: String,
                           fileName: String,
                           source: String,
                           jsPosition: JavaScriptPosition,
                           position: JavaScriptPosition) {
  override def toString: String = s"$source:${position.line}:${position.column} ($className.$methodName)"
}

object JavaScriptTrace {
  implicit val rw: ReadWriter[JavaScriptTrace] = macroRW
}

case class JavaScriptPosition(line: Int, column: Int) {
  override def toString: String = s"Position(line: $line, column: $column)"
}

object JavaScriptPosition {
  implicit val rw: ReadWriter[JavaScriptPosition] = macroRW
}