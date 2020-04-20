package io.youi.component.selection

class SelectionListener[T](selection: Selection[T], base: html.Element, deferToRoot: Boolean, includeChildTargets: Boolean) {
  var active: Boolean = false

  private val events = new HTMLEvents(selection, base)

  events.pointer.down.attach { evt =>
    if (includeChildTargets || evt.htmlEvent.target == base) {
      val active = selection.down(evt.htmlMouseEvent.getOrElse(throw new RuntimeException("Not a mouse event")))
      if (deferToRoot && active) {
        selection.rootListener.active = active
      } else {
        this.active = active
      }
    }
  }

  events.pointer.move.attach { evt =>
    if (active) selection.move(evt.htmlMouseEvent.getOrElse(throw new RuntimeException("Not a mouse event")))
  }

  events.pointer.up.attach { evt =>
    if (active) {
      selection.up(evt.htmlMouseEvent.getOrElse(throw new RuntimeException("Not a mouse event")))
      active = false
    }
  }
}
