package org.hyperscala.fabricjs

import org.hyperscala.Container
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
}

object Canvas {
  def apply(container: Container[BodyChild], width: Int, height: Int, id: String = Unique()) = {
    val t = new tag.Canvas(id = id, width = width, height = height)
    container.contents += t

    new Canvas(t)
  }
}