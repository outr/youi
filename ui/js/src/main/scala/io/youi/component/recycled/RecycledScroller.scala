package io.youi.component.recycled

import io.youi.component.{Component, Container}
import io.youi.layout.VerticalLayout
import io.youi.style.Overflow
import io.youi.util.Time
import reactify.{Val, Var}

import scala.concurrent.duration._
import scribe.Execution.global

class RecycledScroller[T, C <: Component](perPage: Int, renderer: RecycledRenderer[T, C]) extends Container { scroller =>
  object batch {
    val data: Var[BatchedData[T]] = Var(BatchedData.empty[T])
    val value: Var[Option[T]] = Var(None)
    val position: Var[Int] = Var(math.min(1, data().total))
  }

  overflow := Overflow.Hidden

  protected val pane1 = new RecycledPane
  protected val pane2 = new RecycledPane
  protected val pane3 = new RecycledPane

  private var middle = pane1
  private var top = pane2
  private var bottom = pane3

  // TODO: create "scroll" in gestures
  event.pointer.wheel.attach { evt =>
    val delta = if (evt.delta.y > middle.size.height()) {
      middle.size.height()
    } else if (evt.delta.y < -middle.size.height()) {
      -middle.size.height()
    } else {
      evt.delta.y
    }
    if (middle.isPartial) {
      // Don't scroll at all
    } else if (middle.start == 0 && middle.position.bottom() - delta < size.height()) {
      middle.position.bottom.static(size.height())
    } else if (top.isPartial && top.position.bottom() - delta > top.renderedHeight) {
      middle.position.y.static(top.renderedHeight)
    } else {
      middle.position.y.static(middle.position.y() - delta)
      if (delta < 0) { // Scrolling up
        if (!top.isPartial && top.position.middle() >= 0.0) { // Make top the new middle
          val pt = top
          val pm = middle
          val pb = bottom
          top = pb
          middle = pt
          bottom = pm

          top.page(middle.end)

          middle.position.y.static(middle.position.y())
          top.position.bottom := middle.position.top
          bottom.position.top := middle.position.bottom
        }
      } else { // Scrolling down
        if (bottom.position.middle() <= size.height()) { // Make bottom the new middle
          val pt = top
          val pm = middle
          val pb = bottom
          top = pm
          middle = pb
          bottom = pt

          bottom.page(middle.start - perPage)

          middle.position.y.static(middle.position.y())
          top.position.bottom := middle.position.top
          bottom.position.top := middle.position.bottom
        }
      }
    }
    updatePosition()
  }

  batch.data.attach { data =>
    middle.page(0)
    top.page(perPage)
    bottom.clear()
    val position = if (data.isEmpty) {
      0
    } else {
      1
    }
    batch.position := position
  }

  pane1.position.bottom := size.height
  pane2.position.bottom := pane1.position.top
  pane3.position.top := pane1.position.bottom

  children ++= List(pane1, pane2, pane3)

  private var updatingPosition = false

  batch.position.attach { p =>
    if (!updatingPosition) {
      if (batch.data().isEmpty) {
        batch.position := 0
      } else if (p < 1) {
        batch.position := 1
      } else if (p > batch.data().total) {
        batch.position := batch.data.total
      } else {
        val entryHeight = middle.components.head.size.height()
        val perScreen = math.floor(size.height() / entryHeight).toInt
        val actualPosition = math.min(batch.data.total - (perScreen - 1), p)
        val offset = actualPosition % perPage
        val middleStart = actualPosition - offset
        middle.page(middleStart)
        if (middleStart > 0) {
          bottom.page(middleStart - perPage)
        }
        top.page(middleStart + perPage)
        val adjust = (offset - 1) * entryHeight
        middle.position.bottom.static(scroller.size.height() + adjust)
        Time.delay(10.millis).foreach { _ =>
          updatePosition()
        }
      }
    }
  }

  private def updatePosition(): Unit = {
    bottom.lastItemVisible.orElse(middle.lastItemVisible) match {
      case Some((item, p)) => {
        updatingPosition = true
        try {
          val entryHeight = middle.components.head.size.height()
          val perScreen = math.floor(size.height() / entryHeight).toInt
          if (batch.data.total == 0) {
            batch.value := None
            batch.position := 0
          } else if (p + perScreen >= batch.data.total) {
            batch.data().get(batch.data.total - 1, batch.data.total).foreach { v =>
              updatingPosition = true
              try {
                batch.value := v.headOption
                batch.position := batch.data.total
              } finally {
                updatingPosition = false
              }
            }
          } else {
            batch.value := Some(item)
            batch.position := p + 1
          }
        } finally {
          updatingPosition = false
        }
      }
      case None => // Nothing to show
    }
  }

  class RecycledPane extends Container {
    var start: Int = 0
    var end: Int = 0
    val components: Vector[C] = (0 until perPage).toVector.map(_ => renderer.createComponent)
    components.foreach { c =>
      renderer.loading(c)
      renderer.hide(c)
    }

    layout := new VerticalLayout
    size.width := scroller.size.width
    size.height := components.last.position.bottom
    children ++= components

    def isPartial: Boolean = end - start < perPage
    def renderedHeight: Double = (0 until end - start).foldLeft(0.0)((height, index) => {
      height + components(components.length - (index + 1)).size.height()
    })
    def lastItemVisible: Option[(T, Int)] = if (end - start == 0 || scroller.size.height() - position.top() < 0.0) {
      None
    } else {
      val offset = scroller.size.height() - (position.top() + 10.0)
      components.reverse.zipWithIndex.collectFirst {
        case (c, index) if c.position.top() <= offset && c.position.bottom() >= offset => (renderer.getData(c), start + index)
      }
    }

    def page(start: Int): Unit = if (batch.data().nonEmpty) {
      // Set all to loading
      components.foreach(renderer.loading)
      // Asynchronously load data
      batch.data().get(start, components.length).map { data =>
        this.start = start
        this.end = start + data.length
        update(data)
      }
    } else {
      // No data, hide it all
      components.foreach(renderer.hide)
    }

    def clear(): Unit = {
      start = 0
      end = 0
      components.foreach(renderer.hide)
    }

    def update(data: Vector[T]): Unit = {
      components.zipWithIndex.foreach {
        case (c, index) => data.lift(perPage - (index + 1)) match {
          case Some(t) => {
            renderer.setData(t, c)
            renderer.show(c)
          }
          case None => renderer.hide(c)
        }
      }
    }
  }
}