package io.youi.component.layout

import io.youi.component.Component

case class Snap(component: Component, horizontal: Option[HorizontalSnapping] = None) {
  def rightTo(right: => Double): Snap = {
    val h = horizontal.getOrElse(HorizontalSnapping()).copy(right = Some(() => right))
    val s = copy(horizontal = Some(h))
    Snap.map += component -> s
    h.init(component)     // TODO: move elsewhere
    s
  }
}

object Snap {
  private var map = Map.empty[Component, Snap]

  // TODO: support resetting on change

  def apply(component: Component): Snap = synchronized {
    map.get(component) match {
      case Some(snap) => snap
      case None => {
        val snap = Snap(component, None)
        map += component -> snap
        snap
      }
    }
  }
}

case class HorizontalSnapping(left: Option[() => Double] = None,
                              right: Option[() => Double] = None) {
  def init(component: Component): Unit = {
    left match {
      case Some(l) => {
        right match {
          case Some(r) => {
            component.position.left := l()
            component.size.width := r() - l()
          }
          case None => component.position.left := l()
        }
      }
      case None => {
        right match {
          case Some(r) => component.position.right := r()
          case None => // Nothing to do here
        }
      }
    }
  }
}

// Snap(component).leftTo(other.right).rightTo(ui.size.width)