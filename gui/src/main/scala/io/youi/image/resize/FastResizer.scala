package io.youi.image.resize

import cats.effect.IO
import io.youi._
import org.scalajs.dom.html

import scala.scalajs.js.|

object FastResizer extends ImageResizer {
  override protected[resize] def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): IO[html.Canvas] = {
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    IO.pure(destination)
  }
}
