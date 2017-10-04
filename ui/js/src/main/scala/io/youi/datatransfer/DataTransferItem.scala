package io.youi.datatransfer

import org.scalajs.dom.File

import scala.scalajs.js

@js.native
trait DataTransferItem extends js.Object {
  def kind: String = js.native
  def `type`: String = js.native
  def getAsFile(): File = js.native
  def getAsString(): String = js.native
  def webkitGetAsEntry(): FileSystemEntry = js.native
}
