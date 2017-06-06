package io.youi.component.extra

import io.youi.Color
import io.youi.component.{DrawableComponent, PaintSupport, PaintTheme}
import io.youi.component.draw.path.{Path, PathAction, Rectangle}
import io.youi.component.draw.{Drawable, Fill, Group}
import io.youi.component.event.{DragSupport, MouseEvent}
import io.youi.style.Cursor
import io.youi.theme.RectangularSelectionTheme
import reactify._

// TODO: create Paintable mix-in to define the fill, stroke, lineWidth, etc. info
// TODO: customizable paint and stroke + theme integration (RectangularSelectionTheme)
// TODO: aspect ratio support
// TODO: extract mouse monitoring to mix-in
class RectangularSelection extends DrawableComponent {
  override lazy val theme: Var[_ <: RectangularSelectionTheme] = Var(RectangularSelection)

  private val mouseX = Var(0.0)
  private val mouseY = Var(0.0)

  event.mouse.moveInside.attach { evt =>
    mouseX := evt.x
    mouseY := evt.y
  }

  object selection extends PaintSupport {
    val x1: Var[Double] = Var(0.0)
    val y1: Var[Double] = Var(0.0)
    val x2: Var[Double] = Var(0.0)
    val y2: Var[Double] = Var(0.0)
    val width: Val[Double] = Val(x2 - x1)
    val height: Val[Double] = Val(y2 - y1)
    val edgeDistance: Var[Double] = Var(5.0)

    val minX: Var[Double] = Var(edgeDistance)
    val minY: Var[Double] = Var(edgeDistance)
    val maxX: Var[Double] = Var(size.width - edgeDistance)
    val maxY: Var[Double] = Var(size.height - edgeDistance)
    val minWidth: Var[Double] = Var(30.0)
    val minHeight: Var[Double] = Var(30.0)

    override protected def paintTheme: PaintTheme = theme.selection

    def set(x1: => Double, y1: => Double, x2: => Double, y2: => Double): Unit = {
      this.x1 := x1
      this.y1 := y1
      this.x2 := x2
      this.y2 := y2
    }
  }
  object blocks extends PaintSupport {
    val size: Var[Double] = Var(10.0)

    override protected def paintTheme: PaintTheme = theme.blocks
  }

  object dashes extends PaintSupport {
    override protected def paintTheme: PaintTheme = theme.dashes
  }

  object modal extends PaintSupport {
    override protected def paintTheme: PaintTheme = theme.modal
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
    selection.fill.value,
    selection.stroke.value
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
      blocks.fill.value,
      blocks.stroke.value
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
    modal.fill.value,
    modal.stroke.value
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
      dashes.fill.value,
      dashes.stroke.value
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
          var x1 = value.x1 + adjustX
          var x2 = value.x2 + adjustX
          var y1 = value.y1 + adjustY
          var y2 = value.y2 + adjustY

          if (x1 < rs.selection.minX()) {
            val a = rs.selection.minX - x1
            x1 += a
            x2 += a
          } else if (x2 > rs.selection.maxX()) {
            val a = x2 - rs.selection.maxX
            x1 -= a
            x2 -= a
          }
          if (y1 < rs.selection.minY()) {
            val a = rs.selection.minY - y1
            y1 += a
            y2 += a
          } else if (y2 > rs.selection.maxY()) {
            val a = y2 - rs.selection.maxY
            y1 -= a
            y2 -= a
          }

          rs.selection.x1.setStatic(x1)
          rs.selection.x2.setStatic(x2)
          rs.selection.y1.setStatic(y1)
          rs.selection.y2.setStatic(y2)
        }
        case Cursor.ResizeWest => {
          var x1 = math.max(value.x1 + adjustX, rs.selection.minX)
          if (rs.selection.x2 - x1 < rs.selection.minWidth()) {
            x1 = rs.selection.x2 - rs.selection.minWidth
          }
          rs.selection.x1.setStatic(x1)
        }
        case Cursor.ResizeEast => {
          var x2 = math.min(value.x2 + adjustX, rs.selection.maxX)
          if (x2 - rs.selection.x1 < rs.selection.minWidth()) {
            x2 = rs.selection.x1 + rs.selection.minWidth
          }
          rs.selection.x2.setStatic(x2)
        }
        case Cursor.ResizeNorth => {
          var y1 = math.max(value.y1 + adjustY, rs.selection.minY)
          if (rs.selection.y2 - y1 < rs.selection.minHeight()) {
            y1 = rs.selection.y2 - rs.selection.minHeight
          }
          rs.selection.y1.setStatic(y1)
        }
        case Cursor.ResizeSouth => {
          var y2 = math.min(value.y2 + adjustY, rs.selection.maxY)
          if (y2 - rs.selection.y1 < rs.selection.minHeight()) {
            y2 = rs.selection.y1 + rs.selection.minHeight
          }
          rs.selection.y2.setStatic(y2)
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

object RectangularSelection extends RectangularSelectionTheme