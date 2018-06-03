package io.youi.layout.snap

import io.youi.component.Component

case class HorizontalSnapping(left: Option[() => Double] = None,
                              right: Option[() => Double] = None) {
  def connect(component: Component): Unit = {
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

  def disconnect(component: Component): Unit = {
    component.position.x.static(component.position.x())
    if (left.nonEmpty && right.nonEmpty) {
      component.size.width.static(component.size.width())
    }
  }
}
