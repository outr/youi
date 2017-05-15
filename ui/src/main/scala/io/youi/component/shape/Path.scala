package io.youi.component.shape

import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class Path(actions: List[PathAction]) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = actions.foreach { action =>
    action.invoke(context)
  }
}

object Path {
  private lazy val actionCharacters = Set('M', 'L', 'C', 'Q', 'Z')

  private lazy val MoveRegex = """M([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val LineRegex = """L([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val CurveRegex = """C([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val QuadraticRegex = """Q([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)""".r

  def apply(pathString: String): Path = {
    val b = new StringBuilder
    val actions = ListBuffer.empty[PathAction]
    def s2a(s: String): PathAction = s match {
      case MoveRegex(x, y) => MoveTo(x.toDouble, y.toDouble)
      case LineRegex(x, y) => LineTo(x.toDouble, y.toDouble)
      case CurveRegex(x1, y1, x2, y2, x, y) => CurveTo(x1.toDouble, y1.toDouble, x2.toDouble, y2.toDouble, x.toDouble, y.toDouble)
      case QuadraticRegex(x1, y1, x, y) => QuadraticCurveTo(x1.toDouble, y1.toDouble, x.toDouble, y.toDouble)
      case "Z" => ClosePath
    }

    pathString.foreach { c =>
      if (actionCharacters.contains(c)) {
        if (b.nonEmpty) {
          actions += s2a(b.toString())
        }
        b.clear()
      }
      b.append(c)
    }
    actions += s2a(b.toString())

    Path(actions.toList)
  }
}

sealed trait PathAction {
  def invoke(context: CanvasRenderingContext2D): Unit
}

case class MoveTo(x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.moveTo(x, y)
}

case class LineTo(x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.lineTo(x, y)
}

case class CurveTo(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.bezierCurveTo(x1, y1, x2, y2, x, y)
}

case class QuadraticCurveTo(x1: Double, y1: Double, x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.quadraticCurveTo(x1, y1, x, y)
}

object ClosePath extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.closePath()
}