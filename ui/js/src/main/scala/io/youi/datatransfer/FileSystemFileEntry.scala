package io.youi.datatransfer

@js.native
trait FileSystemFileEntry extends FileSystemEntry {
  def file(callback: js.Function1[File, Unit]): File = js.native
}
