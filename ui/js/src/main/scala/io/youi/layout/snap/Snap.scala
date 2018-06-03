package io.youi.layout.snap

import io.youi.component.Component

case class Snap(component: Component,
                horizontal: Option[HorizontalSnapping],
                vertical: Option[VerticalSnapping]) {
  def leftTo(left: => Double): Snap = {
    val h = horizontal.getOrElse(HorizontalSnapping()).copy(left = Some(() => left))
    val s = copy(horizontal = Some(h))
    Snap.map += component -> s
    h.connect(component)
    s
  }
  def rightTo(right: => Double): Snap = {
    val h = horizontal.getOrElse(HorizontalSnapping()).copy(right = Some(() => right))
    val s = copy(horizontal = Some(h))
    Snap.map += component -> s
    h.connect(component)
    s
  }
  def horizontalReset(): Snap = horizontal match {
    case Some(h) => {
      h.disconnect(component)
      val s = copy(horizontal = None)
      Snap.map += component -> s
      s
    }
    case None => this
  }
  def topTo(top: => Double): Snap = {
    val v = vertical.getOrElse(VerticalSnapping()).copy(top = Some(() => top))
    val s = copy(vertical = Some(v))
    Snap.map += component -> s
    v.connect(component)
    s
  }
  def bottomTo(bottom: => Double): Snap = {
    val v = vertical.getOrElse(VerticalSnapping()).copy(bottom = Some(() => bottom))
    val s = copy(vertical = Some(v))
    Snap.map += component -> s
    v.connect(component)
    s
  }
  def verticalReset(): Snap = vertical match {
    case Some(v) => {
      v.disconnect(component)
      val s = copy(vertical = None)
      Snap.map += component -> s
      s
    }
    case None => this
  }
  def reset(): Snap = horizontalReset().verticalReset()
}

object Snap {
  private var map = Map.empty[Component, Snap]

  // TODO: support resetting on change

  def apply(component: Component): Snap = synchronized {
    map.get(component) match {
      case Some(snap) => snap
      case None => {
        val snap = Snap(component, None, None)
        map += component -> snap
        snap
      }
    }
  }
}