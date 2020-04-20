package io.youi.datatransfer

@js.native
trait FileSystemDirectoryEntry extends FileSystemEntry {
  def createReader(): FileSystemDirectoryReader = js.native
}
