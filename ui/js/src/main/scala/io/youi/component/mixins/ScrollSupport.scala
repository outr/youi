package io.youi.component.mixins

import io.youi.Axis
import io.youi.component.AbstractContainer
import io.youi.drawable.Context
import reactify._

trait ScrollSupport extends AbstractContainer {
  object scroll {
    object horizontal {
      val enabled: Var[Boolean] = Var(false)
      val bar: Var[ScrollBar] = Var(ScrollBar.None)
      def apply(delta: Double): Unit = if (size.measured.width() > size.width()) {
        val value = offset.x + delta
        val min = 0
        val max = size.measured.width() - size.width()
        offset.x.static(math.max(max, math.min(min, value)))
      } else {
        offset.x.static(0.0)
      }
      def left(): Unit = apply(0.0)
      def right(): Unit = apply(Int.MaxValue)
    }
    object vertical {
      val enabled: Var[Boolean] = Var(true)
      val bar: Var[ScrollBar] = Var(ScrollBar.None)
      def apply(delta: Double): Unit = if (size.measured.height() > size.height()) {
        val value = offset.y + delta
        val min = 0
        val max = -(size.measured.height() - size.height())
        offset.y.static(math.max(max, math.min(min, value)))
      } else {
        offset.y.static(0.0)
      }
      def top(): Unit = apply(0.0)
      def bottom(): Unit = apply(Int.MinValue)
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

  override protected def postDraw(context: Context): Unit = {
    super.postDraw(context)

    val height = (size.height / size.measured.height) * size.height
    val width = (size.width / size.measured.width) * size.width
    val x = (-offset.x / (size.measured.width - size.width)) * (size.width - width)
    val y = (-offset.y / (size.measured.height - size.height)) * (size.height - height)
    scroll.horizontal.bar.draw(this, context, Axis.Horizontal, x, width)
    scroll.vertical.bar.draw(this, context, Axis.Vertical, y, height)
  }
}