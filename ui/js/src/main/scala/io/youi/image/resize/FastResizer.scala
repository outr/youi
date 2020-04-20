package io.youi.image.resize

import scala.concurrent.Future

object FastResizer extends ImageResizer {
  override protected[resize] def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    Future.successful(destination)
  }
}
