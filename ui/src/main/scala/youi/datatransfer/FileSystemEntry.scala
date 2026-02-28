package youi.datatransfer

import org.scalajs.dom.File

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait FileSystemEntry extends js.Object {
  def isFile: Boolean = js.native
  def isDirectory: Boolean = js.native
  def name: String = js.native
}

@js.native
trait FileSystemFileEntry extends FileSystemEntry {
  def file(callback: js.Function1[File, Unit]): Unit = js.native
}

@js.native
trait FileSystemDirectoryEntry extends FileSystemEntry {
  def createReader(): FileSystemDirectoryReader = js.native
}

@js.native
trait FileSystemDirectoryReader extends js.Object {
  def readEntries(callback: js.Function1[js.Array[FileSystemEntry], Unit]): Unit = js.native
}
