package io.youi.datatransfer

import org.scalajs.dom.File

import scala.scalajs.js

@js.native
trait FileSystemFileEntry extends FileSystemEntry {
  def file(callback: js.Function1[File, Unit]): File = js.native
}
