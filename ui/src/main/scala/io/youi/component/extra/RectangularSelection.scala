package io.youi.component.extra

import io.youi.Color
import io.youi.component.DrawableComponent
import io.youi.component.draw.path.{Path, PathAction, Rectangle}
import io.youi.component.draw.{Drawable, Fill, Group, Stroke}
import io.youi.component.event.{DragSupport, MouseEvent}
import io.youi.style.Cursor
import reactify._

class RectangularSelection extends DrawableComponent {
  private val mouseX = Var(0.0)
  private val mouseY = Var(0.0)

  event.mouse.moveInside.attach { evt =>
    mouseX := evt.x
    mouseY := evt.y
  }

  object selection {
    val x1: Var[Double] = Var(0.0)
    val y1: Var[Double] = Var(0.0)
    val x2: Var[Double] = Var(0.0)
    val y2: Var[Double] = Var(0.0)
    val width: Val[Double] = Val(x2 - x1)
    val height: Val[Double] = Val(y2 - y1)
    val edgeDistance: Var[Double] = Var(5.0)
    // TODO: paint and stroke detail

    def set(x1: => Double, y1: => Double, x2: => Double, y2: => Double): Unit = {
      this.x1 := x1
      this.y1 := y1
      this.x2 := x2
      this.y2 := y2
    }
  }
  object blocks {
    val size: Var[Double] = Var(10.0)
  }

  private val dragging = new SelectionDragSupport(this)

  drawable := {
    if (selection.width() != 0.0 && selection.height() != 0.0) {
      Group(
        createModal(),
        createDashes(),
        createRectangle(),
        createEdges()
      )
    } else {
      Drawable.empty
    }
  }

  cursor := {
    val ed = selection.edgeDistance()
    if (mouseX >= selection.x1 - ed && mouseX <= selection.x2 + ed && mouseY >= selection.y1 - ed && mouseY <= selection.y2 + ed) {
      if (near(selection.x1, mouseX)) {
        if (near(selection.y1, mouseY)) {
          Cursor.ResizeNorthWest
        } else if (near(selection.y2, mouseY)) {
          Cursor.ResizeSouthWest
        } else {
          Cursor.ResizeWest
        }
      } else if (near(selection.x2, mouseX)) {
        if (near(selection.y1, mouseY)) {
          Cursor.ResizeNorthEast
        } else if (near(selection.y2, mouseY)) {
          Cursor.ResizeSouthEast
        } else {
          Cursor.ResizeEast
        }
      } else if (near(selection.y1, mouseY)) {
        Cursor.ResizeNorth
      } else if (near(selection.y2, mouseY)) {
        Cursor.ResizeSouth
      } else {
        Cursor.Move
      }
    } else {
      Cursor.Default
    }
  }

  protected def createRectangle(): Drawable = Group(
    Path
      .begin
      .rect(selection.x1(), selection.y1(), selection.width(), selection.height())
      .close,
    Stroke(Some(stroke()), lineDash = Some(lineDash()), lineWidth = Some(lineWidth()))
  )
  protected def createEdges(): Drawable = {
    val halfBlock = blocks.size() / 2.0
    def block(x: Double, y: Double): PathAction = {
      Rectangle(x - halfBlock, y - halfBlock, blocks.size(), blocks.size())
    }
    Group(
      Path
        .begin
        .withAction(block(selection.x1, selection.y1))
        .withAction(block(selection.x1 + (selection.width / 2.0), selection.y1))
        .withAction(block(selection.x2, selection.y1))
        .withAction(block(selection.x1, selection.y1 + (selection.height / 2.0)))
        .withAction(block(selection.x2, selection.y1 + (selection.height / 2.0)))
        .withAction(block(selection.x1, selection.y2))
        .withAction(block(selection.x1 + (selection.width / 2.0), selection.y2))
        .withAction(block(selection.x2, selection.y2))
        .close,
      Fill(Some(Color.Green))
    )
  }
  protected def createModal(): Drawable = Group(
    Path
      .begin
      .rect(0.0, 0.0, selection.x1(), size.height())
      .rect(selection.x2(), 0.0, size.width() - selection.x2(), size.height())
      .rect(selection.x1(), 0.0, selection.width(), selection.y1())
      .rect(selection.x1(), selection.y2(), selection.width(), size.height() - selection.y2())
      .close,
    Fill(Some(Color.Black.withAlpha(0.5)))
  )

  protected def createDashes(): Drawable = {
    val horizontalThird = selection.width() / 3.0
    val verticalThird = selection.height() / 3.0
    Group(
      Path
        .begin
        .rect(selection.x1, selection.y1 + verticalThird, selection.width, verticalThird)
        .rect(selection.x1 + horizontalThird, selection.y1, horizontalThird, selection.height)
        .close,
      Stroke(Some(Color.Blue.withAlpha(0.5)), lineWidth = Some(1.0), lineDash = Some(List(5.0, 10.0)))
    )
  }

  private def near(from: Double, to: Double): Boolean = {
    math.abs(from - to) <= selection.edgeDistance()
  }

  override protected def autoPaint = false
}

class SelectionDragSupport(rs: RectangularSelection) extends DragSupport[DragStart](rs) {
  override def draggable(mouseEvent: MouseEvent): Option[DragStart] = {
    val x1 = rs.selection.x1() - rs.selection.edgeDistance()
    val y1 = rs.selection.y1() - rs.selection.edgeDistance()
    val x2 = rs.selection.x2() + rs.selection.edgeDistance()
    val y2 = rs.selection.y2() + rs.selection.edgeDistance()
    if (mouseEvent.x >= x1 && mouseEvent.x <= x2 && mouseEvent.y >= y1 && mouseEvent.y <= y2) {
      Some(DragStart(rs.cursor(), rs.selection.x1(), rs.selection.y1(), rs.selection.x2(), rs.selection.y2(), mouseEvent.globalX, mouseEvent.globalY))
    } else {
      None
    }
  }

  override def dragging(mouseEvent: MouseEvent, value: DragStart): Unit = {
    super.dragging(mouseEvent, value)
    val adjustX = mouseEvent.globalX - value.mouseX
    val adjustY = mouseEvent.globalY - value.mouseY
    def processCursor(cursor: Cursor): Unit = {
      cursor match {
        case Cursor.Move => {
          rs.selection.x1.setStatic(value.x1 + adjustX)
          rs.selection.x2.setStatic(value.x2 + adjustX)
          rs.selection.y1.setStatic(value.y1 + adjustY)
          rs.selection.y2.setStatic(value.y2 + adjustY)
        }
        case Cursor.ResizeNorth => {
          rs.selection.y1.setStatic(value.y1 + adjustY)
        }
        case Cursor.ResizeSouth => {
          rs.selection.y2.setStatic(value.y2 + adjustY)
        }
        case Cursor.ResizeEast => {
          rs.selection.x2.setStatic(value.x2 + adjustX)
        }
        case Cursor.ResizeWest => {
          rs.selection.x1.setStatic(value.x1 + adjustX)
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

case class DragStart(cursor: Cursor, x1: Double, y1: Double, x2: Double, y2: Double, mouseX: Double, mouseY: Double)