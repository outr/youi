package youi.component

import youi.Color
import youi.component.support.BorderSupport
import youi.component.types.{Border, BorderStyle, Visibility}
import youi.event.EventSupport
import youi.spatial.BoundingBox
import org.scalajs.dom
import org.scalajs.dom.html
import reactify.{Channel, Var}

abstract class RectangularSelection[T](
  root: html.Element,
  elements: => Seq[T],
  autoStyle: Boolean = true,
  adjustX: => Double = dom.window.pageXOffset,
  adjustY: => Double = dom.window.pageYOffset
) extends Container with BorderSupport with EventSupport {
  element.style.pointerEvents = "none"

  val enabled: Var[Boolean] = Var(true)
  val x1: Var[Double] = Var(0.0)
  val y1: Var[Double] = Var(0.0)
  val x2: Var[Double] = Var(0.0)
  val y2: Var[Double] = Var(0.0)
  val highlighted: Var[Set[T]] = Var(Set.empty)
  val selected: Var[Set[T]] = Var(Set.empty)

  val highlightAdded: Channel[T] = Channel[T]
  val highlightRemoved: Channel[(T, Boolean)] = Channel[(T, Boolean)]
  val selectionAdded: Channel[T] = Channel[T]
  val selectionRemoved: Channel[T] = Channel[T]

  visibility @= Visibility.Hidden
  if (autoStyle) {
    border @= Border(1.0, BorderStyle.Solid, youi.Color.Black)
  }
  position.z @= 9999

  position.x := math.min(x1(), x2())
  position.y := math.min(y1(), y2())
  size.width := math.abs(x1() - x2())
  size.height := math.abs(y1() - y2())

  def boxFor(element: T): BoundingBox

  highlighted.changes {
    case (oldValue, newValue) =>
      val removed = oldValue -- newValue
      val added = newValue -- oldValue
      removed.foreach(e => highlightRemoved @= (e, selected().contains(e)))
      added.foreach(e => highlightAdded @= e)
  }

  selected.changes {
    case (oldValue, newValue) =>
      val removed = oldValue -- newValue
      val added = newValue -- oldValue
      removed.foreach(e => selectionRemoved @= e)
      added.foreach(e => selectionAdded @= e)
  }

  private def selectElements(sx1: Double, sy1: Double, sx2: Double, sy2: Double): Set[T] = {
    val minX = math.min(sx1, sx2) - adjustX
    val minY = math.min(sy1, sy2) - adjustY
    val maxX = math.max(sx1, sx2) - adjustX
    val maxY = math.max(sy1, sy2) - adjustY
    val bb = BoundingBox(minX, minY, maxX, maxY)
    elements.filter(e => bb.intersects(boxFor(e))).toSet
  }

  protected def updateHighlighting(sx1: Double, sy1: Double, sx2: Double, sy2: Double): Unit = {
    highlighted @= selectElements(sx1, sy1, sx2, sy2)
  }

  protected def selectHighlighted(toggle: Boolean): Unit = {
    val items = if (toggle) {
      selected().diff(highlighted()) ++ highlighted().diff(selected())
    } else {
      selected() ++ highlighted()
    }
    highlighted @= Set.empty
    selected @= items
  }

  private var active = false

  root.addEventListener("mousedown", (evt: dom.MouseEvent) => {
    if (enabled()) {
      val touching = selectElements(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
      if (touching.isEmpty) {
        evt.preventDefault()
        evt.stopPropagation()
        x1 @= evt.pageX
        y1 @= evt.pageY
        x2 @= evt.pageX
        y2 @= evt.pageY
        visibility @= Visibility.Visible
        active = true
      }
    }
  })

  root.addEventListener("mousemove", (evt: dom.MouseEvent) => {
    if (enabled()) {
      if (active) {
        evt.preventDefault()
        evt.stopPropagation()
        x2 @= evt.pageX
        y2 @= evt.pageY
        updateHighlighting(x1(), y1(), x2(), y2())
      } else if (evt.shiftKey || evt.ctrlKey) {
        updateHighlighting(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
      }
    }
  })

  root.addEventListener("mouseup", (evt: dom.MouseEvent) => {
    if (enabled()) {
      if (active) {
        evt.preventDefault()
        evt.stopPropagation()
        selectHighlighted(evt.ctrlKey)
        x1 @= 0.0
        y1 @= 0.0
        x2 @= 0.0
        y2 @= 0.0
        visibility @= Visibility.Hidden
        active = false
      } else if (evt.ctrlKey) {
        updateHighlighting(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
        selectHighlighted(toggle = true)
      } else if (evt.shiftKey) {
        val items = selectElements(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
        items.headOption.foreach { item =>
          selected().lastOption match {
            case Some(last) =>
              val allElems = elements
              val fromIndex = allElems.indexOf(last)
              val toIndex = allElems.indexOf(item)
              if (fromIndex >= 0 && toIndex >= 0) {
                val start = math.min(fromIndex, toIndex)
                val end = math.max(fromIndex, toIndex)
                highlighted @= allElems.slice(start, end + 1).toSet
                selectHighlighted(toggle = false)
              }
            case None =>
              highlighted @= Set(item)
              selectHighlighted(toggle = false)
          }
        }
      }
    }
  })
}
