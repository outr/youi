package io.youi.component.font

import opentype.OpenType
import reactify.{Val, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Font private(val fontFuture: Future[opentype.Font]) {
  val internal: Var[Option[opentype.Font]] = Var[Option[opentype.Font]](None)
  val loaded: Val[Boolean] = Val(internal().nonEmpty)

  fontFuture.map { f =>
    internal := Some(f)
  }
}

object Font {
  private var pathMap = Map.empty[String, Font]

  def fromPath(path: String): Font = synchronized {
    pathMap.get(path) match {
      case Some(font) => font
      case None => {
        val font = new Font(OpenType.load(path))
        pathMap += path -> font
        font
      }
    }
  }
}