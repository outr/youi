package io.youi.component.extra

import io.youi.component.DrawableComponent
import io.youi.component.draw.{BoundingBox, Drawable, Group}
import io.youi.component.draw.path.Path
import io.youi.style.Cursor
import reactify.Var

class RectangularSelection extends DrawableComponent {
  val enabled: Var[Boolean] = Var(true)

  private val edgeDistance = 10.0

  private val mouseX = Var(0.0)
  private val mouseY = Var(0.0)

  event.mouse.moveInside.attach { evt =>
    mouseX := evt.x
    mouseY := evt.y
  }

  cursor := {
    if (near(0.0, mouseX)) {
      if (near(0.0, mouseY)) {
        Cursor.ResizeNorthWest
      } else if (near(size.height, mouseY)) {
        Cursor.ResizeSouthWest
      } else {
        Cursor.ResizeWest
      }
    } else if (near(size.width, mouseX)) {
      if (near(0.0, mouseY)) {
        Cursor.ResizeNorthEast
      } else if (near(size.height, mouseY)) {
        Cursor.ResizeSouthEast
      } else {
        Cursor.ResizeEast
      }
    } else if (near(0.0, mouseY)) {
      Cursor.ResizeNorth
    } else if (near(size.height(), mouseY)) {
      Cursor.ResizeSouth
    } else {
      Cursor.Move
    }
  }

  drawable := {
    if (enabled() && size.width() != 0.0 && size.height() != 0.0) {
      Group(List(
        Some(createRectangle()),
        createEdge(position.left(), position.top()),
        createEdge(position.right(), position.top()),
        createEdge(position.right(), position.bottom()),
        createEdge(position.left(), position.bottom())
      ).flatten)
    } else {
      Drawable.empty
    }
  }

  private def near(from: Double, to: Double): Boolean = {
    math.abs(from - to) <= edgeDistance
  }

  protected def createRectangle(): Drawable = Path.begin.rect(0.0, 0.0, size.width(), size.height()).close
  protected def createEdge(x: Double, y: Double): Option[Drawable] = None
}