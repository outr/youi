package io.youi.datatransfer

import scala.language.implicitConversions

object FileSystemSupport {
  implicit def dataTransfer2FileSystem(dataTransfer: DataTransfer): FileSystemDataTransfer = {
    dataTransfer.asInstanceOf[FileSystemDataTransfer]
  }

  def files(evt: DragEvent, manager: DataTransferManager): Boolean = try {
    evt.dataTransfer.items.toList.foreach(item => fileListBuilder(item.webkitGetAsEntry(), Nil, manager))
    true
  } catch {
    case t: Throwable => {
      false
    }
  }

  private def fileListBuilder(entry: FileSystemEntry,
                              path: List[String],
                              manager: DataTransferManager): Unit = {
    if (entry.isDirectory) {
      val dir = entry.asInstanceOf[FileSystemDirectoryEntry]
      val subPath = path ::: List(dir.name)
      dir.createReader().readEntries((children: js.Array[FileSystemEntry]) => {
        children.toList.foreach(child => fileListBuilder(child, subPath, manager))
      })
    } else if (entry.isFile) {
      val file = entry.asInstanceOf[FileSystemFileEntry]
      val fileName = file.name match {
        case n if n.indexOf('/') != -1 => n.substring(n.lastIndexOf('/') + 1)
        case n => n
      }
      file.file((f: File) => {
        manager.fileReceived @= DataTransferFile(f, fileName, path)
      })
    } else {
      throw new RuntimeException(s"Neither a directory nor file: ${entry.name}")
    }
  }
}
