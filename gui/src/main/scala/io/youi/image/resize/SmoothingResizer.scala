package io.youi.image.resize

import cats.effect.IO
import io.youi._
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.|

class SmoothingResizer(mode: String) extends ImageResizer {
  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): IO[html.Canvas] = {
    destination.context.asInstanceOf[js.Dynamic].imageSmoothingEnabled = true
    destination.context.asInstanceOf[js.Dynamic].imageSmoothingQuality = mode
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    IO.pure(destination)
  }
}
