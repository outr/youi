package io.youi.image.resize

import io.youi._
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.scalajs.js.|

object FastResizer extends ImageResizer {
  override protected[resize] def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    Future.successful(destination)
  }
}
