package io.youi.image.resize

import com.outr.pica.{Pica, ResizeOptions}
import io.youi.SingleThreadedFuture
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.scalajs.js.|

object PicaResizer extends ImageResizer {
  private lazy val pica: Pica = Pica()
  private val picaFuture = new SingleThreadedFuture()

  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    picaFuture {
      pica.resize(source, destination, new ResizeOptions {
        alpha = true
      }).toFuture
    }
  }
}
