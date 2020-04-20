package io.youi.datatransfer

trait DragTarget {
  def manager: DataTransferManager
  private var entered = 0

  protected def dragEnter(): Unit = {
    entered += 1
    showOverlay()
  }

  protected def dragLeave(): Unit = {
    entered -= 1
    if (entered <= 0) {
      hideOverlay()
    }
  }

  protected def dropped(evt: DragEvent): Unit = {
    hideOverlay()

    if (!FileSystemSupport.files(evt, manager)) {
      scribe.info("Unable to use FileSystemSupport, falling back on basic files...")
      manager.process(evt.dataTransfer.files)
    }
  }

  private def showOverlay(): Unit = if (manager.enabled()) {
    manager.overlay.current @= Some(this)
    manager.overlay.visible @= true
  }

  private def hideOverlay(): Unit = if (manager.enabled()) {
    entered = 0
    manager.overlay.visible @= false
    manager.overlay.current @= None
  }
}

class HTMLDragTarget(element: html.Element, val manager: DataTransferManager) extends DragTarget {
  element.addEventListener("dragenter", (evt: Event) => {
    evt.preventDefault()
    dragEnter()
  })

  element.addEventListener("dragleave", (_: Event) => {
    dragLeave()
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
    dropped(evt)
  }, useCapture = false)
}
