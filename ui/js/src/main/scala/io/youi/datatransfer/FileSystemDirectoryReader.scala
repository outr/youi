package io.youi.datatransfer

@js.native
trait FileSystemDirectoryReader extends js.Object {
  def readEntries(successCallback: js.Function1[js.Array[FileSystemEntry], Unit],
                  errorCallback: js.Function = js.native): Unit = js.native
}
