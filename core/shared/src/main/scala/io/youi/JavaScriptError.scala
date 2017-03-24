package io.youi

case class JavaScriptError(message: String,
                           source: String,
                           fileName: String,
                           jsPosition: Position,
                           position: Position,
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

case class JavaScriptTrace(className: String,
                           methodName: String,
                           fileName: String,
                           source: String,
                           jsPosition: Position,
                           position: Position) {
  override def toString: String = s"$source:${position.line}:${position.column} ($className.$methodName)"
}

case class Position(line: Int, column: Int) {
  override def toString: String = s"Position(line: $line, column: $column)"
}