package io.youi.datatransfer

import org.scalajs.dom.{document, html, DragEvent, Event, File}
import reactify.Channel

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object DataTransferManager {
  val fileReceived: Channel[DataTransferFile] = Channel[DataTransferFile]

  def addDragTarget(element: html.Element): Unit = {
    val overlay = document.createElement("div").asInstanceOf[html.Div]
    overlay.style.display = "none"
    overlay.style.position = "absolute"
    overlay.style.top = "0"
    overlay.style.left = "0"
    overlay.style.width = "100%"
    overlay.style.height = "100%"
    overlay.style.backgroundColor = "rgba(0, 0, 0, 0.3)"
    overlay.style.zIndex = "10000"
    overlay.style.pointerEvents = "none"
    element.style.position = "relative"
    element.appendChild(overlay)

    var dragCounter = 0

    element.addEventListener("dragenter", (e: DragEvent) => {
      e.preventDefault()
      dragCounter += 1
      overlay.style.display = "block"
    })

    element.addEventListener("dragover", (e: DragEvent) => {
      e.preventDefault()
      e.dataTransfer.dropEffect = "copy".asInstanceOf[org.scalajs.dom.DataTransferDropEffectKind]
    })

    element.addEventListener("dragleave", (_: DragEvent) => {
      dragCounter -= 1
      if (dragCounter <= 0) {
        dragCounter = 0
        overlay.style.display = "none"
      }
    })

    element.addEventListener("drop", (e: DragEvent) => {
      e.preventDefault()
      dragCounter = 0
      overlay.style.display = "none"

      val dt = e.dataTransfer
      if (dt.items != null && dt.items.length > 0) {
        processItems(dt.items)
      } else if (dt.files != null) {
        processFileList(dt.files)
      }
    })
  }

  def addInput(multiple: Boolean = false,
               accept: String = "",
               directory: Boolean = false): html.Input = {
    val input = document.createElement("input").asInstanceOf[html.Input]
    input.`type` = "file"
    input.multiple = multiple
    if (accept.nonEmpty) input.accept = accept
    if (directory) {
      input.setAttribute("webkitdirectory", "")
      input.setAttribute("directory", "")
    }
    input.style.display = "none"
    document.body.appendChild(input)
    input.addEventListener("change", (_: Event) => {
      processFileList(input.files)
      input.value = ""
    })
    input
  }

  private def processFileList(files: org.scalajs.dom.FileList): Unit = {
    for (i <- 0 until files.length) {
      val file = files(i)
      val path = file.asInstanceOf[js.Dynamic].webkitRelativePath.asInstanceOf[js.UndefOr[String]]
        .toOption
        .filter(_.nonEmpty)
        .getOrElse(file.name)
      fileReceived @= DataTransferFile(file, path)
    }
  }

  private def processItems(items: org.scalajs.dom.DataTransferItemList): Unit = {
    for (i <- 0 until items.length) {
      val item = items(i)
      if (item.kind == "file") {
        val entry = item.asInstanceOf[js.Dynamic].webkitGetAsEntry()
        if (entry != null && !js.isUndefined(entry)) {
          traverseEntry(entry, "")
        } else {
          val file = item.getAsFile()
          if (file != null) {
            fileReceived @= DataTransferFile(file, file.name)
          }
        }
      }
    }
  }

  private def traverseEntry(entry: js.Dynamic, basePath: String): Unit = {
    if (entry.isFile.asInstanceOf[Boolean]) {
      entry.file((file: File) => {
        val path = if (basePath.nonEmpty) s"$basePath/${entry.name.asInstanceOf[String]}" else entry.name.asInstanceOf[String]
        fileReceived @= DataTransferFile(file, path)
      })
    } else if (entry.isDirectory.asInstanceOf[Boolean]) {
      val dirName = entry.name.asInstanceOf[String]
      val dirPath = if (basePath.nonEmpty) s"$basePath/$dirName" else dirName
      val reader = entry.createReader()
      reader.readEntries((entries: js.Array[js.Dynamic]) => {
        entries.foreach(e => traverseEntry(e, dirPath))
      })
    }
  }
}
