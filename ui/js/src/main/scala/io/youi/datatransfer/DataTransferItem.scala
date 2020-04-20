package io.youi.datatransfer

@js.native
trait DataTransferItem extends js.Object {
  def kind: String = js.native
  def `type`: String = js.native
  def getAsFile(): File = js.native
  def getAsString(): String = js.native
  def webkitGetAsEntry(): FileSystemEntry = js.native
}
