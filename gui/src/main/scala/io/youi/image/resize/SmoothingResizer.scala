package io.youi.image.resize

import rapid.Task
import io.youi._
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.|

class SmoothingResizer(mode: String) extends ImageResizer {
  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Task[html.Canvas] = {
    destination.context.asInstanceOf[js.Dynamic].imageSmoothingEnabled = true
    destination.context.asInstanceOf[js.Dynamic].imageSmoothingQuality = mode
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    Task.pure(destination)
  }
}
