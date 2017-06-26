package io.youi.component.draw.path

import io.youi.component.Component
import io.youi.component.draw.{BoundingBox, Drawable}
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class Path(actions: List[PathAction]) extends Drawable with PathBuilder with PathAction {
  override lazy val boundingBox: BoundingBox = BoundingBox(actions)

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = invoke(context)

  override def invoke(context: CanvasRenderingContext2D): Unit = actions.foreach { action =>
    action.invoke(context)
  }

  def shift(adjustX: Double, adjustY: Double): Path = {
    val updated = actions.map {
      case CurveTo(x1, y1, x2, y2, x, y) => CurveTo(x1 + adjustX, y1 + adjustY, x2 + adjustX, y2 + adjustY, x + adjustX, y + adjustY)
      case LineTo(x, y) => LineTo(x + adjustX, y + adjustY)
      case MoveTo(x, y) => MoveTo(x + adjustX, y + adjustY)
      case QuadraticCurveTo(x1, y1, x, y) => QuadraticCurveTo(x1 + adjustX, y1 + adjustY, x + adjustX, y + adjustY)
      case action => action
    }
    Path(updated)
  }

  def fix(): Path = {
    def r(value: Double): Double = math.floor(value) + 0.5
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
}

object Path extends PathBuilder {
  lazy val empty: Path = Path(Nil)

  private lazy val actionCharacters = Set('M', 'L', 'C', 'Q', 'Z')

  private lazy val MoveRegex = """M[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)""".r
  private lazy val LineRegex = """L[ ]?([-]?[0-9.]+)[ ]?([-]?[0-9.]+)""".r
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
    def s2a(s: String): PathAction = s match {
      case MoveRegex(x, y) => MoveTo(x.toDouble, y.toDouble)
      case LineRegex(x, y) => LineTo(x.toDouble, y.toDouble)
      case CurveRegex(x1, y1, x2, y2, x, y) => CurveTo(x1.toDouble, y1.toDouble, x2.toDouble, y2.toDouble, x.toDouble, y.toDouble)
      case QuadraticRegex(x1, y1, x, y) => QuadraticCurveTo(x1.toDouble, y1.toDouble, x.toDouble, y.toDouble)
      case "Z" => ClosePath
      case _ => throw new RuntimeException(s"Unknown action: [$s]")
    }

    pathString.toUpperCase.foreach { c =>
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

  override def withAction(action: PathAction): Path = Path(List(action))
}

trait PathBuilder {
  def begin: Path = withAction(BeginPath)
  def close: Path = withAction(ClosePath)
  def curve(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double): Path = {
    withAction(CurveTo(x1, y1, x2, y2, x, y))
  }
  def line(x: Double, y: Double): Path = withAction(LineTo(x, y))
  def move(x: Double, y: Double): Path = withAction(MoveTo(x, y))
  def quadratic(x1: Double, y1: Double, x: Double, y: Double): Path = withAction(QuadraticCurveTo(x1, y1, x, y))
  def rect(x: Double, y: Double, width: Double, height: Double): Path = withAction(Rectangle(x, y, width, height))

  def withAction(action: PathAction): Path
}