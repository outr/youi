package io.youi.component.shape

import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class Path(actions: List[PathAction]) extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = actions.foreach { action =>
    action.invoke(context)
  }
}

object Path {
  private lazy val actionCharacters = Set('M', 'L', 'C', 'Q', 'Z')

  private lazy val MoveRegex = """M([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val LineRegex = """L([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val CurveRegex = """C([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)""".r
  private lazy val QuadraticRegex = """Q([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)([- ]?[0-9.]+)""".r

  def apply(actions: PathAction*): Path = {
    var list = actions.toList
    if (list.head != BeginPath) {
      list = BeginPath :: list
    }
    if (list.last != ClosePath) {
      list = list ::: List(ClosePath)
    }
    Path(list)
  }

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