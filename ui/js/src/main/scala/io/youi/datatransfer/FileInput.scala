package io.youi.datatransfer

import org.scalajs.dom.{Event, document, html}

class FileInput(input: html.Input, modify: Boolean, folderSupport: Boolean, manager: DataTransferManager) {
  if (modify) {
    input.style.display = "none"
    input.`type` = "file"
    input.multiple = true
    if (folderSupport) {
      input.setAttribute("webkitDirectory", "webkitDirectory")
    } else if (input.hasAttribute("webkitDirectory")) {
      input.removeAttribute("webkitDirectory")
    }
    if (Option(input.parentElement).isEmpty) {
      document.body.appendChild(input)
    }
  }
  input.addEventListener("change", (_: Event) => {
    manager.process(input.files)
  })

  def open(): Unit = input.click()
}
