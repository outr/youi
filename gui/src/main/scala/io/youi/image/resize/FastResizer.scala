package io.youi.image.resize

import rapid.Task
import io.youi._
import org.scalajs.dom.html

import scala.scalajs.js.|

object FastResizer extends ImageResizer {
  override protected[resize] def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Task[html.Canvas] = {
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    Task.pure(destination)
  }
}
