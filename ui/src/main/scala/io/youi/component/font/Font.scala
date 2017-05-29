package io.youi.component.font

import io.youi.component.Component
import io.youi.component.shape.{BoundingBox, Drawable, Path, TouchData}
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

case class TextPaths(paths: Vector[TextPath]) extends Drawable {
  lazy val boundingBox: BoundingBox = {
    var bb = paths.head.path.boundingBox
    paths.tail.foreach(other => bb = bb.merge(other.path.boundingBox))
    bb
  }

  def zero(): TextPaths = TextPaths(paths.zipWithIndex.map {
    case (tp, index) => TextPath(tp.char, index, tp.path.shift(boundingBox.adjustX, boundingBox.adjustY))
  })

  def touching(x: Double, y: Double): Vector[Touching] = {
    val ry = boundingBox.height - y
    paths.flatMap { tp =>
      tp.path.boundingBox.touching(x, ry).map(d => Touching(tp, d))
    }.sortBy(_.data.distance)
  }

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    context.translate(boundingBox.adjustX, boundingBox.adjustY)
    paths.foreach { textPath =>
      textPath.path.draw(component, context)
    }
  }
}

case class TextPath(char: Char, index: Int, path: Path)

case class Touching(textPath: TextPath, data: TouchData) {
  override def toString: String = s"Touching(char: ${textPath.char}, data: $data)"
}