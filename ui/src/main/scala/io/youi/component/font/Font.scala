package io.youi.component.font

import io.youi.component.shape.Path
import opentype.{OpenType, PathOptions}
import reactify.{Val, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Font {
  val loaded: Val[Boolean]

  def createPath(value: String, size: Double, kerning: Boolean): FontPath
}

class OpenTypeFont(val fontFuture: Future[opentype.Font]) extends Font {
  val internal: Var[Option[opentype.Font]] = Var[Option[opentype.Font]](None)
  override val loaded: Val[Boolean] = Val(internal().nonEmpty)

  fontFuture.map { f =>
    internal := Some(f)
  }

  override def createPath(value: String, size: Double, kerning: Boolean): FontPath = {
    val f = internal().getOrElse(throw new RuntimeException(s"Cannot create path from an OpenTypeFont that has not yet completed loading."))
    val k = kerning
    val p = f.getPath(value, 0.0, 0.0, size, new PathOptions {
      kerning = k
    })
    val box = p.getBoundingBox()
    val width = box.x2 - box.x1
    val height = box.y2 - box.y1
    val adjustX = -box.x1
    val adjustY = -box.y2
    FontPath(Path(p.toPathData()), adjustX, adjustY, width, height)
  }
}

case class FontPath(path: Path, adjustX: Double, adjustY: Double, width: Double, height: Double)

object Font {
  case object empty extends Font {
    override val loaded: Val[Boolean] = Val(false)

    override def createPath(value: String, size: Double, kerning: Boolean): FontPath = {
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