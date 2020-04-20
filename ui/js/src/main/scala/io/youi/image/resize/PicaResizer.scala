package io.youi.image.resize

import scala.concurrent.Future

object PicaResizer extends ImageResizer {
  private lazy val pica: Pica = Pica()

  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    KeyedSequence(this) {
      pica.resize(source, destination, new ResizeOptions {
        alpha = true
      }).toFuture
    }
  }
}