package io.youi.path

import io.youi.Context
import io.youi.spatial.BoundingBox

import scala.collection.mutable.ListBuffer

case class Path(actions: List[PathAction]) extends PathBuilder with PathAction {
  lazy val boundingBox: BoundingBox = Path.boundingBox(actions)

  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    actions.foreach(_.draw(context, x, y, scaleX, scaleY))
  }

  def shift(adjustX: Double, adjustY: Double): Path = {
    val updated = actions.map {
      case CurveTo(x1, y1, x2, y2, x, y) => CurveTo(x1 + adjustX, y1 + adjustY, x2 + adjustX, y2 + adjustY, x + adjustX, y + adjustY)
      case LineTo(x, y) => LineTo(x + adjustX, y + adjustY)
      case MoveTo(x, y) => MoveTo(x + adjustX, y + adjustY)
      case QuadraticCurveTo(x1, y1, x, y) => QuadraticCurveTo(x1 + adjustX, y1 + adjustY, x + adjustX, y + adjustY)
      case action => throw new RuntimeException(s"Unsupported PathAction: $action.")
    }
    Path(updated)
  }

  def flipVertically(): Path = {
    val updated = actions.map {
      case CurveTo(x1, y1, x2, y2, x, y) => CurveTo(x1, -y1, x2, -y2, x, -y)
      case LineTo(x, y) => LineTo(x, -y)
      case MoveTo(x, y) => MoveTo(x, -y)
      case QuadraticCurveTo(x1, y1, x, y) => QuadraticCurveTo(x1, -y1, x, -y)
      case action => throw new RuntimeException(s"Unsupported PathAction: $action.")
    }
    Path(updated)
  }

  def fix(): Path = {
    def r(d: Double): Double = Path.fix(d)
    val updated = actions.map {
      case CurveTo(x1, y1, x2, y2, x, y) => CurveTo(r(x1), r(y1), r(x2), r(y2), r(x), r(y))
      case LineTo(x, y) => LineTo(r(x), r(y))
      case MoveTo(x, y) => MoveTo(r(x), r(y))
      case QuadraticCurveTo(x1, y1, x, y) => QuadraticCurveTo(r(x1), r(y1), r(x), r(y))
      case action => action
    }
    Path(updated)
  }

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
      case Rectangle(x, y, width, height) => adjustTo(x + width, y + height, updateCoordinates = false)
      case RoundedRectangle(x, y, width, height, _) => adjustTo(x + width, y + height, updateCoordinates = false)
    }
    BoundingBox(minX, minY, maxX, maxY)
  }

  override def withAction(action: PathAction): Path = Path(List(action))

  def fix(value: Double): Double = math.floor(value) + 0.5
}