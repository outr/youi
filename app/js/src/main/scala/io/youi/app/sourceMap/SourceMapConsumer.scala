package io.youi.app.sourceMap

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}

@js.native
@JSGlobal("window.sourceMap.SourceMapConsumer")
class SourceMapConsumer(rawSourceMap: js.Object) extends js.Object {
  def sources: js.Array[String] = js.native
  def originalPositionFor(position: js.Object): SourcePosition = js.native
  def generatedPositionFor(position: js.Object): js.Object = js.native
}

@js.native
@JSGlobal("window.sourceMap.SourcePosition")
class SourcePosition(val source: String, val line: Int, val column: Int, val name: String) extends js.Object