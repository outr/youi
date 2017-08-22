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