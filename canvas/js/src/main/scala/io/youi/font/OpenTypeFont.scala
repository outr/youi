package io.youi.font

import io.youi.path.Path
import opentype.PathOptions
import reactify.{Val, Var}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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
