package org.hyperscala.fabricjs

import org.hyperscala.Container
import org.hyperscala.fabricjs.event._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.tag
import org.powerscala.{Unique, Color}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Canvas(canvas: tag.Canvas) extends StaticCanvas(canvas) {
  override protected def className = "Canvas"

  lazy val centeredRotation = prop("centeredRotation", false)
  lazy val centeredScaling = prop("centeredScaling", false)
  lazy val containerClass = prop("containerClass", "canvas-container")
  lazy val defaultCursor = prop("defaultCursor", "default")
  lazy val freeDrawingCursor = prop("freeDrawingCursor", "crosshair")
  lazy val hoverCursor = prop("hoverCursor", "move")
  lazy val moveCursor = prop("moveCursor", "move")
  lazy val perPixelTargetFind = prop("perPixelTargetFind", false)
  lazy val rotationCursor = prop("rotationCursor", "crosshair")
  lazy val selection = prop("selection", true)
  lazy val selectionBorderColor = prop("selectionBorderColor", Color.immutable(255, 255, 255, 0.3))
  lazy val selectionColor = prop("selectionColor", Color.immutable(100, 100, 255, 0.3))
  lazy val selectionDashArray = prop[Array[Double]]("selectionDashArray", null)
  lazy val selectionLineWidth = prop("selectionLineWidth", 1.0)
  lazy val skipTargetFind = prop("skipTargetFind", false)
  lazy val targetFindTolerance = prop("targetFindTolerance", 0.0)
  lazy val uniScaleTransform = prop("uniScaleTransform", false)

  lazy val objectModifiedEvent = new ObjectModifiedEventProcessor(this)
  lazy val objectRotatingEvent = new ObjectRotatingEventProcessor(this)
  lazy val objectScalingEvent = new ObjectScalingEventProcessor(this)
  lazy val objectMovingEvent = new ObjectMovingEventProcessor(this)
  lazy val objectSelectedEvent = new ObjectSelectedEventProcessor(this)
  lazy val beforeSelectionClearedEvent = new BeforeSelectionClearedEventProcessor(this)
  lazy val selectionClearedEvent = new SelectionClearedEventProcessor(this)
  lazy val selectionCreatedEvent = new SelectionCreatedEventProcessor(this)
  lazy val pathCreatedEvent = new PathCreatedEventProcessor(this)
  lazy val mouseDownEvent = new CanvasMouseDownEventProcessor(this)
  lazy val mouseMoveEvent = new CanvasMouseMoveEventProcessor(this)
  lazy val mouseUpEvent = new CanvasMouseUpEventProcessor(this)
  lazy val mouseOverEvent = new CanvasMouseOverEventProcessor(this)
  lazy val mouseOutEvent = new CanvasMouseOutEventProcessor(this)
}

object Canvas {
  def apply(container: Container[BodyChild], width: Int, height: Int, id: String = Unique()) = {
    val t = new tag.Canvas(id = id, width = width, height = height)
    container.contents += t

    new Canvas(t)
  }
}