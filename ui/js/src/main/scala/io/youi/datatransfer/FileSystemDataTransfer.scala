package io.youi.datatransfer

import scala.scalajs.js

@js.native
trait FileSystemDataTransfer extends js.Object {
  def items: js.Array[DataTransferItem] = js.native
}
