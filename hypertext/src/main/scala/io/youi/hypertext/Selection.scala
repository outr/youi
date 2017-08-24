package io.youi.hypertext

import io.youi.hypertext.border.BorderStyle
import io.youi.{BoundingBox, Color, HTMLEvents}
import org.scalajs.dom.{document, html}
import reactify.{Channel, Var}

/**
  * Selection provides a rectangular selection feature for multi-selecting elements on the screen.
  *
  * @param root the root element to attach events to
  * @param elements function to get a list of all elements to check for selection
  * @param adjustX offset adjustment to properly handle for horizontal scrolling
  * @param adjustY offset adjustment to properly handle for vertical scrolling
  * @tparam T the type of elements to select
  */
abstract class Selection[T](root: html.Element,
                            elements: => Set[T],
                            adjustX: => Double = document.body.scrollLeft,
                            adjustY: => Double = document.body.scrollTop) extends Container {
  element.style.pointerEvents = "none"

  private val rootEvents = new HTMLEvents(root)

  val enabled: Var[Boolean] = Var(true)
  val x1: Var[Double] = Var(0.0)
  val y1: Var[Double] = Var(0.0)
  val x2: Var[Double] = Var(0.0)
  val y2: Var[Double] = Var(0.0)
  val current: Var[Set[T]] = Var(Set.empty)
  val selected: Channel[Set[T]] = Channel[Set[T]]

  // Default settings
  visible := false
  border.style := Some(BorderStyle.Solid)
  border.size := Some(1.0)
  border.color := Some(Color.Black)

  position.x := math.min(x1, x2)
  position.y := math.min(y1, y2)
  size.width := math.abs(x1 - x2)
  size.height := math.abs(y1 - y2)

  rootEvents.mouse.down.attach { evt =>
    if (enabled() && parent().exists(_.visible())) {
      evt.preventDefault()
      evt.stopPropagation()

      x1 := evt.pageX
      y1 := evt.pageY
      x2 := evt.pageX
      y2 := evt.pageY
      visible := true
    }
  }

  rootEvents.mouse.move.attach { evt =>
    if (enabled() && visible()) {
      evt.preventDefault()
      evt.stopPropagation()

      x2 := evt.pageX
      y2 := evt.pageY

      updateSelection()
    }
  }

  rootEvents.mouse.up.attach { evt =>
    if (enabled() && visible()) {
      evt.preventDefault()
      evt.stopPropagation()

      selected := current()
      current().foreach(removed)
      current := Set.empty

      x1 := 0.0
      y1 := 0.0
      x2 := 0.0
      y2 := 0.0
      visible := false
    }
  }

  protected def updateSelection(): Unit = {
    val selected = select(x1, y1, x2, y2, elements)
    if (selected != current()) {
      val r = current() -- selected
      val a = selected -- current()
      r.foreach(removed)
      a.foreach(added)

      current := selected
    }
  }

  def added(element: T): Unit = {}
  def removed(element: T): Unit = {}

  def boxFor(element: T): BoundingBox

  def select(x1: Double, y1: Double, x2: Double, y2: Double, elements: Set[T]): Set[T] = {
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
