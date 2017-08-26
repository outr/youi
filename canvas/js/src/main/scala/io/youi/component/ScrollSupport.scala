package io.youi.component

import reactify._

trait ScrollSupport extends Component {
  // Wheel support
  event.pointer.wheel.attach { evt =>
    scrollVertical(-evt.delta.y)
    scrollHorizontal(evt.delta.x)

    evt.stopPropagation()
    evt.preventDefault()
  }

  event.gestures.pointers.dragged.attach { pointer =>
    scrollVertical(pointer.deltaY)
  }

  def scrollVertical(delta: Double): Unit = {
    val value = offset.y + delta
    val min = 0
    val max = -(size.measured.height() - size.height())
    offset.y.static(math.max(max, math.min(min, value)))
  }

  def scrollHorizontal(delta: Double): Unit = {
    val value = offset.x + delta
    val min = 0
    val max = size.measured.width() - size.width()
    offset.x.static(math.max(max, math.min(min, value)))
  }
}