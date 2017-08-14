package io.youi.datatransfer

import org.scalajs.dom.{DragEvent, Event, html}

class DragTarget(element: html.Element, manager: DataTransferManager) {
  private var entered = 0

  element.addEventListener("dragenter", (evt: Event) => {
    evt.preventDefault()
    entered += 1
    showOverlay()
  })

  element.addEventListener("dragleave", (_: Event) => {
    entered -= 1
    if (entered <= 0) {
      hideOverlay()
    }
  })

  List("drag", "dragstart", "dragend", "dragover").foreach { eventType =>
    element.addEventListener(eventType, (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()
    })
  }

  element.addEventListener("drop", (evt: DragEvent) => {
    evt.preventDefault()
    evt.stopPropagation()
    hideOverlay()

    if (!FileSystemSupport.files(evt, manager)) {
      scribe.info("Unable to use FileSystemSupport, falling back on basic files...")
      manager.process(evt.dataTransfer.files)
    }
  }, useCapture = false)

  private def showOverlay(): Unit = {
    manager.overlay.current := Some(this)
    manager.overlay.visible := true
  }

  private def hideOverlay(): Unit = {
    entered = 0
    manager.overlay.visible := false
    manager.overlay.current := None
  }
}
