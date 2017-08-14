package io.youi.datatransfer

import scala.scalajs.js

@js.native
trait FileSystemDirectoryEntry extends FileSystemEntry {
  def createReader(): FileSystemDirectoryReader = js.native
}
