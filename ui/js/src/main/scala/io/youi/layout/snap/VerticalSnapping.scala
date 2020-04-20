package io.youi.layout.snap

case class VerticalSnapping(top: Option[() => Double] = None,
                            bottom: Option[() => Double] = None) {
  def connect(component: Component): Unit = {
    top match {
      case Some(t) => {
        bottom match {
          case Some(b) => {
            component.position.top := t()
            component.size.height := b() - t()
          }
          case None => component.position.top := t()
        }
      }
      case None => {
        bottom match {
          case Some(b) => component.position.bottom := b()
          case None => // Nothing to do here
        }
      }
    }
  }

  def disconnect(component: Component): Unit = {
    component.position.y.static(component.position.y())
    if (top.nonEmpty && bottom.nonEmpty) {
      component.size.height.static(component.size.height())
    }
  }
}
