package io.youi.component

import io.youi.{BoundingBox, Context}
import io.youi.font.{Font, TextPaths}
import io.youi.paint.Paint
import io.youi.theme.TextTheme
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify._

import scala.concurrent.Future
import scala.scalajs.js

class Text extends Component with TextTheme {
  override lazy val theme: Var[TextTheme] = Var(Text)

  override protected def defaultThemeParent = Some(theme)

  val value: Var[String] = Var("")
  val textSelection: TextSelection = new TextSelection(this)
  val textPaths: Val[TextPaths] = Val {
    if (value().nonEmpty && font.file.loaded) {
      font
        .file()
        .createPaths(value(), font.size(), font.kerning())
        .zero()
    } else {
      TextPaths(Vector.empty)
    }
  }

  size.measured.width := textPaths.boundingBox.width
  size.measured.height := textPaths.boundingBox.height

//  def textPaths: Option[TextPaths] = drawable() match {
//    case tp: TextPaths => Some(tp)
//    case _ => None
//  }

  /*protected def createDrawable(): Drawable = {
    if (value().nonEmpty && font.file.loaded()) {
      try {
        val textPaths = font.file().createPaths(value(), font.size(), font.kerning())
        size.measured.width := textPaths.boundingBox.width
        size.measured.height := textPaths.boundingBox.height
        textPaths.zero()
      } catch {
        case t: Throwable => {
          scribe.error(t)
          throw t
        }
      }
    } else {
      Drawable.empty
    }
  }*/

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (value().nonEmpty) {
      if (shadow.enabled()) {
        context.setShadow(shadow.blur, shadow.color, shadow.x, shadow.y)
      }
      context.lineJoin(lineJoin())
      context.miterLimit(miterLimit())
      context.textBaseline(textBaseline())
      textPaths().draw(this, context.canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D])
      if (fill.nonEmpty) {
        context.fill(fill(), apply = true)
      }
      if (stroke.nonEmpty) {
        context.stroke(stroke(), apply = true)
      }
    }
  }
}

object Text extends TextTheme

class TextSelection(text: Text) {
  val enabled: Var[Boolean] = Var(text.theme.selection.enabled)
  val fill: Var[Paint] = Var(text.theme.selection.fill)
  val stroke: Var[Paint] = Var(text.theme.selection.stroke)

  val value: Var[Option[Selection]] = Var(None)

//  private val dragSupport = new DragSupport[Int](text) {
//    override def draggable(pointer: Pointer): Option[Int] = mouseEventToIndex(pointer.move)
//
//    override def dragging(pointer: Pointer, value: Int): Unit = updateDragging(pointer.move, value)
//  }

  text.event.pointer.down.on(value := None)

//  text.preDraw += this
//  dragSupport.drop.attach(d => scribe.info(s"Dropped: $d"))
  value.on(text.reDraw.flag())

  /*override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    value().foreach { selection =>
      val paths = text.textPaths.get.paths.slice(selection.start, selection.end)
      val first = paths.head.path.boundingBox
      val last = paths.last.path.boundingBox
      val x1 = first.x1
      val y1 = 0.0
      val x2 = last.x2
      val y2 = text.size.height()
      context.fillStyle = fill().apply(text).asInstanceOf[js.Any]
      scribe.info(s"Drawing: $x1, $y1, $x2, $y2")
      context.fillRect(x1, y1, x2 - x1, y2 - y1)
    }
    Future.successful(())
  }

  private def mouseEventToIndex(mouseEvent: MouseEvent): Option[Int] = {
    text.textPaths.flatMap { textPath =>
      textPath.touching(mouseEvent.x, mouseEvent.y).headOption.map { touching =>
        if (touching.data.deltaX < 0.0) {
          touching.textPath.index
        } else {
          touching.textPath.index + 1
        }
      }
    }
  }

  private def updateDragging(mouseEvent: MouseEvent, startIndex: Int): Unit = {
    val selection = if (enabled()) {
      mouseEventToIndex(mouseEvent).flatMap { endIndex =>
        if (startIndex == endIndex) {
          None
        } else {
          val start = math.min(startIndex, endIndex)
          val end = math.max(startIndex, endIndex)
          val v = text.value().substring(start, end)
          Some(Selection(v, start, end, active = true))
        }
      }
    } else {
      None
    }
    if (selection.nonEmpty) value := selection
  }*/
}

case class Selection(value: String, start: Int, end: Int, active: Boolean)