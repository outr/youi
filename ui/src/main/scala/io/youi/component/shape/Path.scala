package io.youi.component.shape

import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class Path(actions: List[PathAction]) extends Drawable {
  lazy val boundingBox: BoundingBox = BoundingBox(actions)

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = actions.foreach { action =>
    action.invoke(context)
  }
}

case class BoundingBox(x1: Double, y1: Double, x2: Double, y2: Double) {
  def adjustX: Double = -x1
  def adjustY: Double = height - y2
  def width: Double = x2 - x1
  def height: Double = y2 - y1

  def merge(that: BoundingBox): BoundingBox = BoundingBox(
    x1 = math.min(this.x1, that.x1),
    y1 = math.min(this.y1, that.y1),
    x2 = math.max(this.x2, that.x2),
    y2 = math.max(this.y2, that.y2)
  )

  def touching(x: Double, y: Double): Boolean = {
    // TODO: fix this
//    scribe.info(s"Checking: $x between $x1 and $x2 and $y between $y1 and $y2 / $adjustY and $height")
    x >= x1 && x <= x2 && y >= 0 && y <= height
  }

  override def toString: String = s"BoundingBox(x1: $x1, y1: $y1, x2: $x2, y2: $y2, adjustX: $adjustX, adjustY: $adjustY, width: $width, height: $height)"
}

object BoundingBox {
  def apply(pathActions: List[PathAction]): BoundingBox = {
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue

    var cx = 0.0
    var cy = 0.0

    def adjustTo(newX: Double, newY: Double, oldX: Double = cx, oldY: Double = cy): Unit = {
      minX = math.min(oldX, math.min(minX, newX))
      minY = math.min(oldY, math.min(minY, newY))
      maxX = math.max(oldX, math.max(maxX, newX))
      maxY = math.max(oldY, math.max(maxY, newY))

      cx = newX
      cy = newY
    }

    pathActions.foreach {
      case BeginPath => // Nothing
      case ClosePath => // Nothing
      case CurveTo(_, _, _, _, x, y) => adjustTo(x, y)
      case LineTo(x, y) => adjustTo(x, y)
      case MoveTo(x, y) => {
        cx = x
        cy = y
      }
      case QuadraticCurveTo(_, _, x, y) => adjustTo(x, y)
    }
    BoundingBox(minX, minY, maxX, maxY)
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
      case _ => throw new RuntimeException(s"Unknown action: [$s]")
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
    if (b.nonEmpty) {
      actions += s2a(b.toString())
    }

    Path(actions.toList)
  }
}