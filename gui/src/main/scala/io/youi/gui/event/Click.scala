package io.youi.gui.event

import io.youi.gui.Component
import reactify.reaction.{Reaction, ReactionStatus}

/*class Click(component: Component, gestures: Gestures) extends Reaction[Pointer] {
  private var lastClick = 0L

  gestures.pointers.removed.attach { p =>
    if (p.identifier == 0 && p.movedFromStart.distance <= gestures.tap.maxDistance() && gestures.pointers.map.isEmpty) {
      if (gestures.tap.enabled() && p.elapsed <= gestures.tap.maxTimeout()) {
        apply(p, None)
      } else if (gestures.longPress.enabled() && p.elapsed >= gestures.longPress.minTimeout()) {
        gestures.longPress(p, None)
      } else if (gestures.doubleClick.enabled() && p.time - lastClick <= gestures.doubleClick.maxDelay()) {
        gestures.doubleClick(p, None)
        lastClick = 0L
      } else {
        gestures.click(p, None)
        lastClick = p.time
      }
    }
  }

  override def apply(value: Pointer, previous: Option[Pointer]): ReactionStatus = ReactionStatus.Continue
}*/
