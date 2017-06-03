package io.youi.component

import io.youi.component.event.{DragSupport, MouseEvent}
import io.youi.component.font.{Font, TextPaths}
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.style.Paint
import io.youi.theme.TextTheme
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.scalajs.js

class Text extends DrawableComponent {
  override lazy val theme: Var[TextTheme] = Var(Text)

  val value: Var[String] = Var("")
  object font {
    val file: Var[Font] = Var(theme.font.file)
    val size: Var[Double] = Var(theme.font.size)
    val kerning: Var[Boolean] = Var(theme.font.kerning)
  }
  val selection: TextSelection = new TextSelection(this)

  drawable := createDrawable()

  def textPaths: Option[TextPaths] = drawable() match {
    case tp: TextPaths => Some(tp)
    case _ => None
  }

  protected def createDrawable(): Drawable = {
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
  }
}

object Text extends TextTheme

class TextSelection(text: Text) extends Drawable {
  val enabled: Var[Boolean] = Var(text.theme.selection.enabled)
  val fill: Var[Paint] = Var(text.theme.selection.fill)
  val stroke: Var[Paint] = Var(text.theme.selection.stroke)

  override def boundingBox: BoundingBox = BoundingBox.zero    // TODO: determine if we need to support this

  val value: Var[Option[Selection]] = Var(None)

  private val dragSupport = new DragSupport[Int](text) {
    override def draggable(mouseEvent: MouseEvent): Option[Int] = mouseEventToIndex(mouseEvent)

    override def dragging(mouseEvent: MouseEvent, value: Int): Unit = updateDragging(mouseEvent, value)
  }

  text.preDraw += this
  dragSupport.drop.attach(d => scribe.info(s"Dropped: $d"))
  value.on(text.reDraw.flag())

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = value().foreach { selection =>
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
    val selection = mouseEventToIndex(mouseEvent).flatMap { endIndex =>
      if (startIndex == endIndex) {
        None
      } else {
        val start = math.min(startIndex, endIndex)
        val end = math.max(startIndex, endIndex)
        val v = text.value().substring(start, end)
        Some(Selection(v, start, end, active = true))
      }
    }
    if (selection.nonEmpty) value := selection
  }
}

case class Selection(value: String, start: Int, end: Int, active: Boolean)