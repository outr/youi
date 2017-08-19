package io.youi.font

import io.youi.BoundingBox
import io.youi.component.Component
import io.youi.event.TouchData
import io.youi.path.Path
import opentype.{OpenType, PathOptions}
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.{Val, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Font {
  val loaded: Val[Boolean]

  def createPaths(value: String, size: Double, kerning: Boolean): TextPaths
}

class OpenTypeFont(val fontFuture: Future[opentype.Font]) extends Font {
  val internal: Var[Option[opentype.Font]] = Var[Option[opentype.Font]](None)
  override val loaded: Val[Boolean] = Val(internal().nonEmpty)

  fontFuture.map { f =>
    internal := Some(f)
  }

  override def createPaths(value: String, size: Double, kerning: Boolean): TextPaths = {
    val f = internal().getOrElse(throw new RuntimeException(s"Cannot create path from an OpenTypeFont that has not yet completed loading."))
    val k = kerning

    val openTypePaths = f.getPaths(value, 0.0, 0.0, size, new PathOptions {
      kerning = k
    })
    val paths = openTypePaths.map(p => Path(p.toPathData()))
    val textPaths = value.zip(paths).zipWithIndex.map {
      case ((char, path), index) => TextPath(char, index, path)
    }.toVector
    TextPaths(textPaths)
  }
}

object Font {
  case object empty extends Font {
    override val loaded: Val[Boolean] = Val(false)

    override def createPaths(value: String, size: Double, kerning: Boolean): TextPaths = {
      throw new RuntimeException("Cannot create path from Font.empty.")
    }
  }

  private var pathMap = Map.empty[String, Font]

  def fromPath(path: String): Font = synchronized {
    pathMap.get(path) match {
      case Some(font) => font
      case None => {
        val font = new OpenTypeFont(OpenType.load(path))
        pathMap += path -> font
        font
      }
    }
  }
}

case class TextPaths(paths: Vector[TextPath]) {
  lazy val boundingBox: BoundingBox = if (paths.nonEmpty) {
    var bb = paths.head.path.boundingBox
    paths.tail.foreach(other => bb = bb.merge(other.path.boundingBox))
    bb
  } else {
    BoundingBox.zero
  }

  def zero(): TextPaths = TextPaths(paths.zipWithIndex.map {
    case (tp, index) => TextPath(tp.char, index, tp.path.shift(boundingBox.adjustX, boundingBox.adjustY))
  })

  private def touchingPath(x: Double, y: Double, x1: Double, y1: Double, x2: Double, y2: Double): Option[TouchData] = {
    val centerX = x1 + ((x2 - x1) / 2.0)
    val centerY = y1 + ((y2 - y1) / 2.0)
    if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
      val deltaX = x - centerX
      val deltaY = y - centerY
      Some(TouchData(deltaX, deltaY, BoundingBox.distance(x, centerX, y, centerY)))
    } else {
      None
    }
  }

  def touching(x: Double, y: Double): Vector[Touching] = {
    val ry = boundingBox.height - y
    paths.flatMap { tp =>
      val bb = tp.path.boundingBox
      touchingPath(x, ry, bb.x1, 0.0, bb.x2, boundingBox.height).map(d => Touching(tp, d))
    }.sortBy(_.data.distance)
  }

  def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    context.translate(boundingBox.adjustX, boundingBox.adjustY)
    Future.sequence(paths.map(_.path.draw(component, context))).map(_ => ())
  }
}

case class TextPath(char: Char, index: Int, path: Path)

case class Touching(textPath: TextPath, data: TouchData) {
  override def toString: String = s"Touching(char: ${textPath.char}, data: $data)"
}