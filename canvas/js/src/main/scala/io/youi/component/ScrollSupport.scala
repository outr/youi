package io.youi.component

import reactify._

trait ScrollSupport extends Component {
  object scroll {
    object horizontal {
      val enabled: Var[Boolean] = Var(false)
      def apply(delta: Double): Unit = {
        val value = offset.x + delta
        val min = 0
        val max = size.measured.width() - size.width()
        offset.x.static(math.max(max, math.min(min, value)))
      }
    }
    object vertical {
      val enabled: Var[Boolean] = Var(true)
      def apply(delta: Double): Unit = {
        val value = offset.y + delta
        val min = 0
        val max = -(size.measured.height() - size.height())
        offset.y.static(math.max(max, math.min(min, value)))
      }
    }
  }

  // Wheel support
  event.pointer.wheel.attach { evt =>
    if (scroll.vertical.enabled()) scroll.vertical(-evt.delta.y)
    if (scroll.horizontal.enabled()) scroll.horizontal(evt.delta.x)

    evt.stopPropagation()
    evt.preventDefault()
  }

  event.gestures.pointers.dragged.attach { pointer =>
    if (scroll.vertical.enabled()) scroll.vertical(pointer.deltaY)
  }
}