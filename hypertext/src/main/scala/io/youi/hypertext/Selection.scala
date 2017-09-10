package io.youi.hypertext

import io.youi._
import io.youi.hypertext.border.BorderStyle
import io.youi.{BoundingBox, Color, HTMLEvents, Key}
import org.scalajs.dom.{document, html}
import reactify._

import scala.collection.immutable.ListSet

/**
  * Selection provides a rectangular selection feature for multi-selecting elements on the screen.
  *
  * @param root the root element to attach events to
  * @param elements function to get a list of all elements to check for selection
  * @param autoStyle if true defines the default styling of the outline (defaults to true)
  * @param adjustX offset adjustment to properly handle for horizontal scrolling
  * @param adjustY offset adjustment to properly handle for vertical scrolling
  * @tparam T the type of elements to select
  */
abstract class Selection[T](root: html.Element,
                            elements: => ListSet[T],
                            autoStyle: Boolean = true,
                            adjustX: => Double = document.body.scrollLeft,
                            adjustY: => Double = document.body.scrollTop) extends Container {
  element.style.pointerEvents = "none"

  private val mouse = ui.mouse

  private val rootEvents = new HTMLEvents(root)

  val enabled: Var[Boolean] = Var(true)
  val time: Var[Long] = Var(0L)
  val x1: Var[Double] = Var(0.0)
  val y1: Var[Double] = Var(0.0)
  val x2: Var[Double] = Var(0.0)
  val y2: Var[Double] = Var(0.0)
  val highlighted: Var[ListSet[T]] = Var(ListSet.empty)
  val selected: Var[ListSet[T]] = Var(ListSet.empty)

  // Default settings
  visible := false
  if (autoStyle) {
    border.style := Some(BorderStyle.Solid)
    border.size := Some(1.0)
    border.color := Some(Color.Black)
  }
  element.style.zIndex = "9999"

  position.x := math.min(x1, x2)
  position.y := math.min(y1, y2)
  size.width := math.abs(x1 - x2)
  size.height := math.abs(y1 - y2)

  rootEvents.pointer.down.attach { evt =>
    if (enabled() && evt.target == root) {
      val touching = select(evt.pageX, evt.pageY, evt.pageX, evt.pageY, elements)
      if (touching.isEmpty) {
        evt.preventDefault()
        evt.stopPropagation()

        time := System.currentTimeMillis()
        x1 := evt.pageX
        y1 := evt.pageY
        x2 := evt.pageX
        y2 := evt.pageY
        visible := true
      }
    }
  }

  rootEvents.pointer.move.attach { evt =>
    if (enabled()) {
      if (visible()) {
        x2 := evt.pageX
        y2 := evt.pageY

        updateHighlighting(x1, y1, x2, y2)
      } else if (evt.getModifierState(Key.Shift.value) || evt.getModifierState(Key.Control.value)) {
        updateHighlighting(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
      }
    }
  }

  ui.event.key.down.attach { evt =>
    if (enabled()) {
      if (!visible()) {
        evt.key match {
          case Key.Shift | Key.Control => updateHighlighting(mouse.x, mouse.y, mouse.x, mouse.y)
          case _ => // Ignore others
        }
      }
    }
  }

  ui.event.key.up.attach { evt =>
    if (enabled()) {
      if (!visible()) {
        evt.key match {
          case Key.Shift | Key.Control => changeHighlighting(ListSet.empty)
          case _ => // Ignore others
        }
      }
    }
  }

  rootEvents.pointer.up.attach { evt =>
    if (enabled()) {
      if (visible()) {
        evt.preventDefault()
        evt.stopPropagation()

        selectHighlighted(evt.getModifierState(Key.Control.value))

        x1 := 0.0
        y1 := 0.0
        x2 := 0.0
        y2 := 0.0
        visible := false
      } else if (evt.getModifierState(Key.Control.value)) {
        updateHighlighting(evt.pageX, evt.pageY, evt.pageX, evt.pageY)
        selectHighlighted(toggle = true)
      } else if (evt.getModifierState(Key.Shift.value)) {
        select(evt.pageX, evt.pageY, evt.pageX, evt.pageY, elements).headOption.foreach { item =>
          selected().lastOption match {
            case Some(last) => {
              val items = elements.dropWhile(_ != last).takeWhile(_ != item) ++ ListSet(item)
              changeHighlighting(items)
              selectHighlighted(toggle = false)
            }
            case None => {
              changeHighlighting(ListSet(item))
              selectHighlighted(toggle = false)
            }
          }
        }
      }
    }
  }

  protected def updateHighlighting(x1: Double, y1: Double, x2: Double, y2: Double): Unit = {
    val highlighting = select(x1, y1, x2, y2, elements)
    changeHighlighting(highlighting)
  }

  protected def changeHighlighting(highlighting: ListSet[T]): Unit = {
    highlighted := highlighting
  }

  protected def selectHighlighted(toggle: Boolean): Unit = {
    val items = if (toggle) {
      val diff1 = selected().diff(highlighted())
      val diff2 = highlighted().diff(selected())
      diff1 ++ diff2
    } else {
      selected() ++ highlighted()
    }
    highlighted := ListSet.empty
    selected.static(items)
  }

  highlighted.changes(new ChangeObserver[ListSet[T]] {
    override def change(oldValue: ListSet[T], newValue: ListSet[T]): Unit = {
      val removed = oldValue -- newValue
      val added = newValue -- oldValue
      removed.foreach { e =>
        val isSelected = selected().contains(e)
        highlightRemoved(e, isSelected)
      }
      added.foreach(highlightAdded)
    }
  })

  selected.changes(new ChangeObserver[ListSet[T]] {
    override def change(oldValue: ListSet[T], newValue: ListSet[T]): Unit = {
      val removed = oldValue -- newValue
      val added = newValue -- oldValue
      removed.foreach(selectionRemoved)
      added.foreach(selectionAdded)
    }
  })

  def highlightAdded(element: T): Unit = {}
  def highlightRemoved(element: T, isSelected: Boolean): Unit = {}

  def selectionAdded(element: T): Unit = {}
  def selectionRemoved(element: T): Unit = {}

  def boxFor(element: T): BoundingBox

  def select(x1: Double, y1: Double, x2: Double, y2: Double, elements: ListSet[T]): ListSet[T] = {
    val minX = math.min(x1, x2) - adjustX
    val minY = math.min(y1, y2) - adjustY
    val maxX = math.max(x1, x2) - adjustX
    val maxY = math.max(y1, y2) - adjustY
    val boundingBox = BoundingBox(minX, minY, maxX, maxY)
    elements.filter { e =>
      val b = boxFor(e)
      boundingBox.intersects(b)
    }
  }
}
