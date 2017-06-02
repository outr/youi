package io.youi.component.extra

import io.youi._
import io.youi.component.{Container, DrawableComponent}
import io.youi.component.draw._
import io.youi.component.draw.path.Path
import io.youi.component.event.{DragSupport, MouseEvent}
import io.youi.style.Cursor
import reactify.Var

class RectangularSelection extends Container {
  // TODO: support max dimensions (minX, minY, maxX, maxY) - this container's dimensions?
  // TODO: support aspect ratio constraints
  // TODO: support minimum width/height of selection
  // TODO: support custom stylization (drawing, stroking, and filling) of points, dashes, and selection

  val selection = new SelectionDrawable
  val modal = new DrawableComponent {
    interactive := false
    drawable := Group(
      Path
        .begin
        .rect(0.0, 0.0, selection.position.x(), ui.size.height())
        .rect(selection.position.right(), 0.0, ui.size.width() - selection.position.right(), ui.size.height())
        .rect(selection.position.x(), 0.0, selection.size.width(), selection.position.y())
        .rect(selection.position.x(), selection.position.bottom(), selection.size.width(), ui.size.height() - selection.position.bottom())
        .close,
      Fill(Some(Color.Black.withAlpha(0.5)))
    )
  }
  val points = new DrawableComponent {
    val blockSize = 10.0
    val halfBlock: Double = blockSize / 2.0

    interactive := false
    drawable := Group(
      Path
        .begin
        .rect(selection.position.left() - halfBlock, selection.position.top() - halfBlock, blockSize, blockSize)        // Top-Left
        .rect(selection.position.right() - halfBlock, selection.position.top() - halfBlock, blockSize, blockSize)       // Top-Right
        .rect(selection.position.left() - halfBlock, selection.position.bottom() - halfBlock, blockSize, blockSize)     // Bottom-Left
        .rect(selection.position.right() - halfBlock, selection.position.bottom() - halfBlock, blockSize, blockSize)    // Bottom-Right
        .rect(selection.position.left() - halfBlock, selection.position.middle() - halfBlock, blockSize, blockSize)     // Left
        .rect(selection.position.right() - halfBlock, selection.position.middle() - halfBlock, blockSize, blockSize)    // Right
        .rect(selection.position.center() - halfBlock, selection.position.top() - halfBlock, blockSize, blockSize)      // Top
        .rect(selection.position.center() - halfBlock, selection.position.bottom() - halfBlock, blockSize, blockSize)   // Bottom
        .close,
      Fill(Some(Color.Green))
    )
  }
  val dashes = new DrawableComponent {
    interactive := false
    val horizontalThird: () => Double = () => selection.size.width() / 3.0
    val verticalThird: () => Double = () => selection.size.height() / 3.0
    drawable := Group(
      Path
        .begin
        .rect(selection.position.left(), selection.position.top() + verticalThird(), selection.size.width(), verticalThird())
        .rect(selection.position.left() + horizontalThird(), selection.position.top(), horizontalThird(), selection.size.height())
        .close,
      Stroke(Some(Color.Blue.withAlpha(0.5)), Some(List(5.0, 10.0)))
    )
  }

  children += modal
  children += dashes
  children += selection
  children += points
}

class SelectionDrawable extends DrawableComponent {
  private val dragSupport = new DragSupport[DragStart](this) {
    override def draggable(mouseEvent: MouseEvent): Option[DragStart] = {
      Some(DragStart(cursor(), position.x(), position.y(), size.width(), size.height(), mouseEvent.globalX, mouseEvent.globalY))
    }

    override def dragging(mouseEvent: MouseEvent, value: DragStart): Unit = {
      super.dragging(mouseEvent, value)

      val adjustX = mouseEvent.globalX - value.mouseX
      val adjustY = mouseEvent.globalY - value.mouseY

      def processCursor(cursor: Cursor): Unit = {
        cursor match {
          case Cursor.Move => {
            position.x.setStatic(value.x + adjustX)
            position.y.setStatic(value.y + adjustY)
          }
          case Cursor.ResizeNorth => {
            position.y.setStatic(value.y + adjustY)
            size.height.setStatic(value.height - adjustY)
          }
          case Cursor.ResizeSouth => {
            size.height.setStatic(value.height + adjustY)
          }
          case Cursor.ResizeEast => {
            size.width.setStatic(value.width + adjustX)
          }
          case Cursor.ResizeWest => {
            position.x.setStatic(value.x + adjustX)
            size.width.setStatic(value.width - adjustX)
          }
          case Cursor.ResizeNorthWest => {
            processCursor(Cursor.ResizeNorth)
            processCursor(Cursor.ResizeWest)
          }
          case Cursor.ResizeNorthEast => {
            processCursor(Cursor.ResizeNorth)
            processCursor(Cursor.ResizeEast)
          }
          case Cursor.ResizeSouthWest => {
            processCursor(Cursor.ResizeSouth)
            processCursor(Cursor.ResizeWest)
          }
          case Cursor.ResizeSouthEast => {
            processCursor(Cursor.ResizeSouth)
            processCursor(Cursor.ResizeEast)
          }
          case _ => scribe.info(s"Ignoring $value")
        }
      }

      processCursor(value.cursor)
    }

    override def dropped(mouseEvent: MouseEvent, value: DragStart): Unit = {
      super.dropped(mouseEvent, value)

      scribe.info(s"Dropped: $value")
    }
  }

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
    if (size.width() != 0.0 && size.height() != 0.0) {
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

  case class DragStart(cursor: Cursor, x: Double, y: Double, width: Double, height: Double, mouseX: Double, mouseY: Double)
}