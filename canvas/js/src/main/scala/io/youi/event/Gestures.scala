package io.youi.event

import io.youi.{BoundingBox, HTMLEvents}
import io.youi.component.Component
import io.youi.spatial.Point
import reactify.{Channel, InvocationType, Observable, Val, Var}

class Gestures(component: Component) {
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

  lazy val pinch: Pinch = new Pinch(component)

  // TODO: `state` to represent drag, swype, fling, pan, rotate

  if (HTMLEvents.hasTouchSupport) {
    component.event.touch.start.attach(add)
    component.event.touch.move.attach(dragging)
    component.event.touch.end.attach(remove)
  } else {
    component.event.pointer.down.attach(add)
    component.event.pointer.move.attach(dragging)
    Observable.wrap(
      component.event.pointer.up,
      component.event.pointer.cancel
    ).attach(remove)
  }


  private def add(evt: PointerEvent): Unit = {
    val p = Pointer(evt.identifier, evt, evt, evt)
    _pointers := _pointers() + (evt.identifier -> p)

    pointers.added := p
  }
  private def dragging(evt: PointerEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    val updated = p.withEvent(evt)

    _pointers := _pointers() + (p.identifier -> updated)
    pointers.dragged := updated
  }
  private def remove(evt: PointerEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    _pointers := _pointers() - evt.identifier

    pointers.removed := p.copy()
  }
}

case class Pointer(identifier: Int,
                   start: PointerEvent,
                   move: PointerEvent,
                   previous: PointerEvent,
                   moved: Moved = Moved(0.0, 0.0, 0.0),
                   movedFromStart: Moved = Moved(0.0, 0.0, 0.0),
                   meanX: List[Double] = Nil,
                   meanY: List[Double] = Nil,
                   meanTime: List[Long] = Nil) {
  val time: Long = System.currentTimeMillis()
  lazy val (velocityX, velocityY) = {
    val count = meanX.size.toDouble
    val averageX = meanX.sum / count
    val averageY = meanY.sum / count
    val averageTime = (meanTime.sum / count) / 1000.0
    (averageX / averageTime, averageY / averageTime)
  }
  lazy val (deltaX, deltaY) = (move.globalX - previous.globalX) -> (move.globalY - previous.globalY)

  def elapsed: Long = time - start.time

  def withEvent(evt: PointerEvent): Pointer = {
    val moved = Moved(move, evt)
    val movedFromStart = Moved(start, evt)
    val mx = (moved.deltaX :: meanX).take(Pointer.sampleSize)
    val my = (moved.deltaY :: meanY).take(Pointer.sampleSize)
    val mt = (elapsed :: meanTime).take(Pointer.sampleSize)
    copy(
      move = evt,
      previous = move,
      moved = moved,
      movedFromStart = movedFromStart,
      meanX = mx,
      meanY = my,
      meanTime = mt
    )
  }

  override def toString: String = s"Pointer(id: $identifier, start: $start, move: $move, velocity: $velocityX x $velocityY, delta: $deltaX x $deltaY)"
}

object Pointer {
  /**
    * The number of samples to use to calculate acceleration and velocity.
    *
    * Defaults to 10
    */
  val sampleSize: Var[Int] = Var(10)
}

case class Moved(deltaX: Double, deltaY: Double, distance: Double)

object Moved {
  def apply(previous: PointerEvent, current: PointerEvent): Moved = {
    val deltaX = current.global.x - previous.global.x
    val deltaY = current.global.y - previous.global.y
    val distance = BoundingBox.distance(previous.global.x, previous.global.y, current.global.x, current.global.y)
    Moved(deltaX, deltaY, distance)
  }
}

class Tap(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(Tap.DefaultEnabled)
  val maxDistance: Var[Double] = Var(Tap.DefaultMaxDistance)
  val maxTimeout: Var[Long] = Var(Tap.DefaultMaxTimeout)

  override def fire(value: Pointer, `type`: InvocationType): Unit = super.fire(value, `type`)
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
        fire(p, InvocationType.Direct)
      } else if (gestures.longPress.enabled() && p.elapsed >= gestures.longPress.minTimeout()) {
        gestures.longPress.fire(p, InvocationType.Direct)
      } else if (gestures.doubleClick.enabled() && p.time - lastClick <= gestures.doubleClick.maxDelay()) {
        gestures.doubleClick.fire(p, InvocationType.Direct)
        lastClick = 0L
      } else {
        gestures.click.fire(p, InvocationType.Direct)
        lastClick = p.time
      }
    }
  }

  override def fire(value: Pointer, `type`: InvocationType): Unit = super.fire(value, `type`)
}

class LongPress(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(LongPress.DefaultEnabled)
  val minTimeout: Var[Long] = Var(LongPress.DefaultMinTimeout)

  override def fire(value: Pointer, `type`: InvocationType): Unit = super.fire(value, `type`)
}

object LongPress {
  val DefaultEnabled: Boolean = true
  val DefaultMinTimeout: Long = 1000L
}

class DoubleClick(component: Component) extends Observable[Pointer] {
  val enabled: Var[Boolean] = Var(DoubleClick.DefaultEnabled)
  val maxDelay: Var[Long] = Var(DoubleClick.DefaultMaxDelay)

  override def fire(value: Pointer, `type`: InvocationType): Unit = super.fire(value, `type`)
}

object DoubleClick {
  val DefaultEnabled: Boolean = true
  val DefaultMaxDelay: Long = 400L
}

class Pinch(component: Component) extends Observable[PinchEvent] {
  import component.event.gestures.pointers

  pointers.dragged.attach(dragging)

  private def dragging(pointer: Pointer): Unit = if (pointers.map.size == 2 && pointer.moved.distance != 0.0) {
    val other = pointers.map.find(_._1 != pointer.identifier).get._2

    val ox = other.move.local.x
    val oy = other.move.local.y
    val op = other.move.local
    val px = pointer.previous.local.x
    val py = pointer.previous.local.y
    val pp = pointer.previous.local
    val cx = pointer.move.local.x
    val cy = pointer.move.local.y
    val cp = pointer.move.local

    val pd = BoundingBox.distance(ox, oy, px, py)
    val cd = BoundingBox.distance(ox, oy, cx, cy)
    val deltaX = cx - px
    val deltaY = cy - py

    val deltaDistance = cd - pd
    val direction = if (deltaDistance > 0.0) {
      PinchDirection.Out
    } else {
      PinchDirection.In
    }
    val pe = PinchEvent(
      pointer,
      previous = PinchState(op, pp, pd),
      current = PinchState(op, cp, cd),
      deltaX = deltaX,
      deltaY = deltaY,
      deltaDistance = cd - pd,
      direction = direction
    )
    fire(pe, InvocationType.Direct)
  }

  override def fire(value: PinchEvent, `type`: InvocationType): Unit = super.fire(value, `type`)
}

case class PinchEvent(pointer: Pointer,
                      previous: PinchState,
                      current: PinchState,
                      deltaX: Double,
                      deltaY: Double,
                      deltaDistance: Double,
                      direction: PinchDirection)

case class PinchState(point1: Point, point2: Point, distance: Double)

sealed trait PinchDirection

object PinchDirection {
  case object In extends PinchDirection
  case object Out extends PinchDirection
}