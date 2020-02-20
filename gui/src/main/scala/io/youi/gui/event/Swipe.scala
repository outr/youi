package io.youi.gui.event

import io.youi.gui.Component
import reactify.Channel

class Swipe(component: Component,
            events: Events,
            onlyTouch: Boolean,
            directions: Set[Swipe.Direction] = Swipe.Direction.All) {
  private var dragging: Option[SwipeEvent] = None

  lazy val start: Channel[SwipeEvent] = Channel[SwipeEvent]
  lazy val move: Channel[SwipeEvent] = Channel[SwipeEvent]
  lazy val end: Channel[SwipeEvent] = Channel[SwipeEvent]

  events.pointers.dragged.attach { p =>
    if (!onlyTouch || p.start.isTouch) {
      val absX = math.abs(p.movedFromStart.deltaX)
      val absY = math.abs(p.movedFromStart.deltaY)
      val plane = dragging.map(_.direction.plane).orElse(
        if (absX > absY && absX > Swipe.Start) {
          Some(Swipe.Plane.Horizontal)
        } else if (absX < absY && absY > Swipe.Start) {
          Some(Swipe.Plane.Vertical)
        } else {
          None
        }
      )
      val option = plane match {
        case Some(Swipe.Plane.Horizontal) if p.deltaX < 0.0 => Some((Swipe.Direction.Left, p.movedFromStart.deltaX, p.velocityX))
        case Some(Swipe.Plane.Horizontal) => Some((Swipe.Direction.Right, p.movedFromStart.deltaX, p.velocityX))
        case Some(Swipe.Plane.Vertical) if p.deltaY < 0.0 => Some((Swipe.Direction.Up, p.movedFromStart.deltaY, p.velocityY))
        case Some(Swipe.Plane.Vertical) => Some((Swipe.Direction.Down, p.movedFromStart.deltaY, p.velocityY))
        case _ => None
      }
      option.foreach {
        case (direction, distance, acceleration) => if (directions.contains(direction)) {
          val event = SwipeEvent(direction, p, distance, acceleration)
          if (dragging.isEmpty) {
            dragging = Some(event)
            start @= event
          }
          move @= event
        }
      }
    }
  }

  events.pointers.removed.on {
    dragging match {
      case Some(event) => {
        end @= event

        dragging = None
      }
      case _ => // Ignore
    }
  }
}

object Swipe {
  /**
    * The distance of the line before it is considering a swipe.
    */
  var Start: Double = 20.0

  sealed trait Direction {
    def plane: Plane
  }

  object Direction {
    case object Up extends Direction {
      override def plane: Plane = Plane.Vertical
    }
    case object Down extends Direction {
      override def plane: Plane = Plane.Vertical
    }
    case object Left extends Direction {
      override def plane: Plane = Plane.Horizontal
    }
    case object Right extends Direction {
      override def plane: Plane = Plane.Horizontal
    }

    lazy val All: Set[Direction] = Set(Up, Down, Left, Right)
  }

  sealed trait Plane

  object Plane {
    case object Horizontal extends Plane
    case object Vertical extends Plane
  }
}