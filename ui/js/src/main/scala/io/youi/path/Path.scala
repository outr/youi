package io.youi.path

import io.youi.drawable.Context
import io.youi.spatial.BoundingBox
import io.youi.ui
import reactify.Val

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

case class Path(actions: List[PathAction]) extends PathBuilder with PathAction {
  lazy val boundingBox: BoundingBox = Path.boundingBox(actions)
  lazy val path2d: Val[Path2D] = Val(createPath2d(ui.ratio))

  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    actions.foreach(_.draw(context, x, y, scaleX, scaleY))
  }

  def modify(mx: Double => Double, my: Double => Double): Path = {
    val updated = actions.map {
      case CurveTo(x1, y1, x2, y2, x, y) => CurveTo(mx(x1), my(y1), mx(x2), my(y2), mx(x), my(y))
      case LineTo(x, y) => LineTo(mx(x), my(y))
      case MoveTo(x, y) => MoveTo(mx(x), my(y))
      case QuadraticCurveTo(x1, y1, x, y) => QuadraticCurveTo(mx(x1), my(y1), mx(x), my(y))
      case Rectangle(x, y, width, height, begin, close) => Rectangle(mx(x), my(y), mx(width), my(height), begin, close)
      case BeginPath => BeginPath
      case ClosePath => ClosePath
      case action => throw new RuntimeException(s"Unsupported PathAction: $action.")
    }
    Path(updated)
  }

  private def createPath2d(ratio: => Double): Path2D = {
    val p = new Path2D
    def s(v: Double): Double = v * ratio
    actions.foreach {
      case CurveTo(x1, y1, x2, y2, x, y) => p.bezierCurveTo(s(x1), s(y1), s(x2), s(y2), s(x), s(y))
      case LineTo(x, y) => p.lineTo(s(x), s(y))
      case MoveTo(x, y) => p.moveTo(s(x), s(y))
      case QuadraticCurveTo(x1, y1, x, y) => p.quadraticCurveTo(s(x1), s(y1), s(x), s(y))
      case BeginPath => // No begin in path2d
      case ClosePath => p.closePath()
      case action => throw new RuntimeException(s"Unsupported PathAction: $action.")
    }
    p
  }

  def scale(x: Double = 1.0, y: Double = 1.0): Path = modify(_ * x, _ * y)

  def shift(adjustX: Double, adjustY: Double): Path = modify(_ + adjustX, _ + adjustY)

  def fix(): Path = modify(Path.fix, Path.fix)

  override def withAction(action: PathAction): Path = Path(actions ::: List(action))

  def withoutOpen(): Path = Path(actions.filterNot(_ == BeginPath))
  def withoutClose(): Path = Path(actions.filterNot(_ == ClosePath))

  override def toString: String = s"Path(${actions.mkString(", ")})"
}

object Path extends PathBuilder {
  lazy val empty: Path = Path(Nil)

  private lazy val actionCharacters = Set('M', 'L', 'C', 'Q', 'Z', 'H', 'V')

  private lazy val MoveRegex = """M[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)""".r
  private lazy val LineRegex = """L[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)""".r
  private lazy val HorizontalLineRegex = """H[ ]?([-]?[0-9.]+)""".r
  private lazy val VerticalLineRegex = """V[ ]?([-]?[0-9.]+)""".r
  private lazy val CurveRegex = """C[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)""".r
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

  def merge(paths: Path*): Path = Path(paths.flatMap(_.actions).toList)

  def apply(pathString: String): Path = {
    val b = new StringBuilder
    val actions = ListBuffer.empty[PathAction]
    var currentX = 0.0
    var currentY = 0.0
    def s2a(s: String): PathAction = s match {
      case MoveRegex(x, y) => {
        currentX = x.toDouble
        currentY = y.toDouble
        MoveTo(currentX, currentY)
      }
      case LineRegex(x, y) => {
        currentX = x.toDouble
        currentY = y.toDouble
        LineTo(currentX, currentY)
      }
      case HorizontalLineRegex(x) => {
        currentX = x.toDouble
        LineTo(currentX, currentY)
      }
      case VerticalLineRegex(y) => {
        currentY = y.toDouble
        LineTo(currentX, currentY)
      }
      case CurveRegex(x1, y1, x2, y2, x, y) => {
        currentX = x.toDouble
        currentY = y.toDouble
        CurveTo(x1.toDouble, y1.toDouble, x2.toDouble, y2.toDouble, currentX, currentY)
      }
      case QuadraticRegex(x1, y1, x, y) => {
        currentX = x.toDouble
        currentY = y.toDouble
        QuadraticCurveTo(x1.toDouble, y1.toDouble, currentX, currentY)
      }
      case "Z" => ClosePath
      case _ => throw new RuntimeException(s"Unknown action: [$s]")
    }

    pathString.replaceAll("[,]", " ").toUpperCase.foreach { c =>
      if (actionCharacters.contains(c)) {
        if (b.nonEmpty) {
          actions += s2a(b.toString().trim)
        }
        b.clear()
      }
      b.append(c)
    }
    if (b.nonEmpty) {
      actions += s2a(b.toString().trim)
    }

    Path(actions.toList)
  }

  def boundingBox(pathActions: List[PathAction]): BoundingBox = {
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue

    var cx = 0.0
    var cy = 0.0

    def adjustTo(newX: Double, newY: Double, oldX: Double = cx, oldY: Double = cy, updateCoordinates: Boolean = true): Unit = {
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
      case Rectangle(x, y, width, height, _, _) => adjustTo(x + width, y + height, updateCoordinates = false)
      case RoundedRectangle(x, y, width, height, _) => adjustTo(x + width, y + height, updateCoordinates = false)
    }
    BoundingBox(minX, minY, maxX, maxY)
  }

  override def withAction(action: PathAction): Path = Path(List(action))

  def fix(value: Double): Double = value
}

@js.native
@JSGlobal("Path2D")
class Path2D extends js.Object {
  def closePath(): Unit = js.native
  def moveTo(x: Double, y: Double): Unit = js.native
  def lineTo(x: Double, y: Double): Unit = js.native
  def bezierCurveTo(cp1x: Double, cp1y: Double, cp2x: Double, cp2y: Double, x: Double, y: Double): Unit = js.native
  def quadraticCurveTo(cpx: Double, cpy: Double, x: Double, y: Double): Unit = js.native
  def arc(x: Double, y: Double, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean = js.native): Unit = js.native
  def arcTo(x1: Double, y1: Double, x2: Double, y2: Double, radius: Double): Unit = js.native
  def ellipse(x: Double, y: Double, radiusX: Double, radiusY: Double, rotation: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean = js.native): Unit = js.native
  def rect(x: Double, y: Double, width: Double, height: Double): Unit = js.native
}