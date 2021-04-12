package io.youi

import fabric.rw._

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
  implicit val rw: ReaderWriter[JavaScriptError] = ccRW
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
  implicit val rw: ReaderWriter[JavaScriptCause] = ccRW
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
  implicit val rw: ReaderWriter[JavaScriptTrace] = ccRW
}

case class JavaScriptPosition(line: Int, column: Int) {
  override def toString: String = s"Position(line: $line, column: $column)"
}

object JavaScriptPosition {
  implicit val rw: ReaderWriter[JavaScriptPosition] = ccRW
}