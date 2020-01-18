package io.youi.image.resize

import com.outr.pica.{Pica, ResizeOptions}
import io.youi.KeyedSequence
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.scalajs.js.|

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