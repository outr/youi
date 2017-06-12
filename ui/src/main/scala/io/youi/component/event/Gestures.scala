package io.youi.component.event

import io.youi.component.Component
import io.youi.component.draw.BoundingBox
import reactify.{Channel, Observable, Val, Var}

class Gestures(component: Component) {
  import component.event.pointer

  private val _pointers = Var[Map[Int, Pointer]](Map.empty)

  object pointers {
    val map: Val[Map[Int, Pointer]] = _pointers
    lazy val all: Val[Vector[Pointer]] = Val(_pointers.values.toVector)
    val added: Channel[Pointer] = Channel[Pointer]
    val dragged: Channel[Pointer] = Channel[Pointer]
    val removed: Channel[Pointer] = Channel[Pointer]

    def get(identifier: Int): Option[Pointer] = map().get(identifier)
    def apply(identifier: Int): Pointer = get(identifier).getOrElse(throw new RuntimeException(s"Unable to find pointer by id: $identifier."))
  }

  lazy val tap: Tap = new Tap(component)
  lazy val click: Click = new Click(component)
  lazy val doubleClick: DoubleClick = new DoubleClick(component)
  lazy val longPress: LongPress = new LongPress(component)

  // TODO: `state` to represent up, drag, pinch, swype, etc.

  pointer.down.attach(add)
  pointer.move.attach { evt =>
    if (evt.inside) {
      dragging(evt)
    }
  }
  Observable.wrap(
    pointer.up,
    pointer.upOutside,
    pointer.cancel
  ).attach(remove)


  private def add(evt: MouseEvent): Unit = {
    val p = Pointer(evt.identifier, evt, evt)
    _pointers := _pointers() + (evt.identifier -> p)

    pointers.added := p
  }
  private def dragging(evt: MouseEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    val start = p.start
    val previous = p.move
    val current = evt

    val moved = Moved(previous, current)
    val movedFromStart = Moved(start, current)
    val updated = p.copy(move = evt, moved = moved, movedFromStart = movedFromStart)

    _pointers := _pointers() + (p.identifier -> updated)
    pointers.dragged := updated
  }
  private def remove(evt: MouseEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    _pointers := _pointers() - evt.identifier

    pointers.removed := p.copy()
  }
}

case class Pointer(identifier: Int,
                   start: MouseEvent,
                   move: MouseEvent,
                   moved: Moved = Moved(0.0, 0.0, 0.0),
                   movedFromStart: Moved = Moved(0.0, 0.0, 0.0)) {
  val time: Long = System.currentTimeMillis()

  def elapsed: Long = time - start.time
}

case class Moved(deltaX: Double, deltaY: Double, distance: Double)

object Moved {
  def apply(previous: MouseEvent, current: MouseEvent): Moved = {
    val deltaX = current.globalX - previous.globalX
    val deltaY = current.globalY - previous.globalY
    val distance = BoundingBox.distance(previous.globalX, previous.globalY, current.globalX, current.globalY)
    Moved(deltaX, deltaY, distance)
  }
}

class Tap(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(Tap.DefaultEnabled)
  val maxDistance: Var[Double] = Var(Tap.DefaultMaxDistance)
  val maxTimeout: Var[Long] = Var(Tap.DefaultMaxTimeout)

  override def fire(value: Pointer): Unit = super.fire(value)
}

object Tap {
  val DefaultEnabled: Boolean = false
  val DefaultMaxDistance: Double = 10.0
  val DefaultMaxTimeout: Long = 200L
}

class Click(component: Component) extends Observable[Pointer] {
  import component.event.gestures
  import component.event.gestures.pointers

  private var lastClick = 0L

  pointers.removed.attach { p =>
    if (p.identifier == 0 && p.movedFromStart.distance <= gestures.tap.maxDistance() && pointers.map.isEmpty) {
      if (gestures.tap.enabled() && p.elapsed <= gestures.tap.maxTimeout()) {
        fire(p)
      } else if (gestures.longPress.enabled() && p.elapsed >= gestures.longPress.minTimeout()) {
        gestures.longPress.fire(p)
      } else if (gestures.doubleClick.enabled() && p.time - lastClick <= gestures.doubleClick.maxDelay()) {
        gestures.doubleClick.fire(p)
        lastClick = 0L
      } else {
        gestures.click.fire(p)
        lastClick = p.time
      }
    }
  }

  override def fire(value: Pointer): Unit = super.fire(value)
}

class LongPress(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(LongPress.DefaultEnabled)
  val minTimeout: Var[Long] = Var(LongPress.DefaultMinTimeout)

  override def fire(value: Pointer): Unit = super.fire(value)
}

object LongPress {
  val DefaultEnabled: Boolean = true
  val DefaultMinTimeout: Long = 1000L
}

class DoubleClick(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(DoubleClick.DefaultEnabled)
  val maxDelay: Var[Long] = Var(DoubleClick.DefaultMaxDelay)

  override def fire(value: Pointer): Unit = super.fire(value)
}

object DoubleClick {
  val DefaultEnabled: Boolean = true
  val DefaultMaxDelay: Long = 400L
}