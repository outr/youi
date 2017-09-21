package io.youi.layout

import io.youi.Widget

case class Snap(widget: Widget,
                horizontal: Option[HorizontalSnapping],
                vertical: Option[VerticalSnapping]) {
  def leftTo(left: => Double): Snap = {
    val h = horizontal.getOrElse(HorizontalSnapping()).copy(left = Some(() => left))
    val s = copy(horizontal = Some(h))
    Snap.map += widget -> s
    h.connect(widget)
    s
  }
  def rightTo(right: => Double): Snap = {
    val h = horizontal.getOrElse(HorizontalSnapping()).copy(right = Some(() => right))
    val s = copy(horizontal = Some(h))
    Snap.map += widget -> s
    h.connect(widget)
    s
  }
  def horizontalReset(): Snap = horizontal match {
    case Some(h) => {
      h.disconnect(widget)
      val s = copy(horizontal = None)
      Snap.map += widget -> s
      s
    }
    case None => this
  }
  def topTo(top: => Double): Snap = {
    val v = vertical.getOrElse(VerticalSnapping()).copy(top = Some(() => top))
    val s = copy(vertical = Some(v))
    Snap.map += widget -> s
    v.connect(widget)
    s
  }
  def bottomTo(bottom: => Double): Snap = {
    val v = vertical.getOrElse(VerticalSnapping()).copy(bottom = Some(() => bottom))
    val s = copy(vertical = Some(v))
    Snap.map += widget -> s
    v.connect(widget)
    s
  }
  def verticalReset(): Snap = vertical match {
    case Some(v) => {
      v.disconnect(widget)
      val s = copy(vertical = None)
      Snap.map += widget -> s
      s
    }
    case None => this
  }
  def reset(): Snap = horizontalReset().verticalReset()
}

object Snap {
  private var map = Map.empty[Widget, Snap]

  // TODO: support resetting on change

  def apply(widget: Widget): Snap = synchronized {
    map.get(widget) match {
      case Some(snap) => snap
      case None => {
        val snap = Snap(widget, None, None)
        map += widget -> snap
        snap
      }
    }
  }
}

case class HorizontalSnapping(left: Option[() => Double] = None,
                              right: Option[() => Double] = None) {
  def connect(widget: Widget): Unit = {
    left match {
      case Some(l) => {
        right match {
          case Some(r) => {
            widget.position.left := l()
            widget.size.width := r() - l()
          }
          case None => widget.position.left := l()
        }
      }
      case None => {
        right match {
          case Some(r) => widget.position.right := r()
          case None => // Nothing to do here
        }
      }
    }
  }

  def disconnect(widget: Widget): Unit = {
    widget.position.x.static(widget.position.x())
    if (left.nonEmpty && right.nonEmpty) {
      widget.size.width.static(widget.size.width())
    }
  }
}

case class VerticalSnapping(top: Option[() => Double] = None,
                            bottom: Option[() => Double] = None) {
  def connect(widget: Widget): Unit = {
    top match {
      case Some(t) => {
        bottom match {
          case Some(b) => {
            widget.position.top := t()
            widget.size.height := b() - t()
          }
          case None => widget.position.top := t()
        }
      }
      case None => {
        bottom match {
          case Some(b) => widget.position.bottom := b()
          case None => // Nothing to do here
        }
      }
    }
  }

  def disconnect(widget: Widget): Unit = {
    widget.position.y.static(widget.position.y())
    if (top.nonEmpty && bottom.nonEmpty) {
      widget.size.height.static(widget.size.height())
    }
  }
}