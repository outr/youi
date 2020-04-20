package io.youi.datatransfer

@js.native
trait FileSystemEntry extends js.Object {
  def fullPath: String = js.native
  def isDirectory: Boolean = js.native
  def isFile: Boolean = js.native
  def name: String = js.native
}
