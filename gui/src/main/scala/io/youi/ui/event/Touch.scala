package io.youi.ui.event

import io.youi.ui.Component
import reactify.standard.StandardChannel

class Touch(component: Component,
            event: Events,
            identifier: Option[Int],
            minimumTime: Long,
            maximumTime: Long,
            maxDistance: Double,
            lastTimeMin: Long,
            lastTimeMax: Long) extends StandardChannel[Pointer](None) {
  private var lastClick = 0L

  event.pointers.removed.attach { p =>
    if (
      (identifier.isEmpty || identifier.contains(p.identifier)) &&
      p.movedFromStart.distance <= maxDistance &&
      p.elapsed >= minimumTime &&
      p.elapsed <= maximumTime &&
      p.time - lastClick >= lastTimeMin &&
      p.time - lastClick <= lastTimeMax
    ) {
      set(p)
    }
    lastClick = p.time
  }
}

object Touch {
  def tap(component: Component with EventSupport): Touch = new Touch(
    component = component,
    event = component.event,
    identifier = None,
    minimumTime = 0L,
    maximumTime = 200L,
    maxDistance = 10.0,
    lastTimeMin = 750L,
    lastTimeMax = Long.MaxValue
  )

  def click(component: Component with EventSupport): Touch = new Touch(
    component = component,
    event = component.event,
    identifier = None,
    minimumTime = 201L,
    maximumTime = 999L,
    maxDistance = 10.0,
    lastTimeMin = 750L,
    lastTimeMax = Long.MaxValue
  )

  def longPress(component: Component with EventSupport): Touch = new Touch(
    component = component,
    event = component.event,
    identifier = None,
    minimumTime = 1000L,
    maximumTime = Long.MaxValue,
    maxDistance = 10.0,
    lastTimeMin = 750L,
    lastTimeMax = Long.MaxValue
  )

  def doubleClick(component: Component with EventSupport): Touch = new Touch(
    component = component,
    event = component.event,
    identifier = None,
    minimumTime = 0L,
    maximumTime = 999L,
    maxDistance = 10.0,
    lastTimeMin = 0L,
    lastTimeMax = 500L
  )
}