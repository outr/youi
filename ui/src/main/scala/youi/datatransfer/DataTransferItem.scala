package youi.datatransfer

import scala.scalajs.js

@js.native
trait DataTransferItem extends js.Object {
  def kind: String = js.native
  def webkitGetAsEntry(): FileSystemEntry = js.native
  def getAsFile(): org.scalajs.dom.File = js.native
}
