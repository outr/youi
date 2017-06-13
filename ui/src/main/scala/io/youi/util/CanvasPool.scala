package io.youi.util

import io.youi._
import org.scalajs.dom.html

object CanvasPool {
  private var cached = List.empty[html.Canvas]

  def apply(width: Double, height: Double): html.Canvas = synchronized {
    val canvas = if (cached.nonEmpty) {
      val c = cached.head
      cached = cached.tail
      c
    } else {
      dom.create[html.Canvas]("canvas")
    }
    val w = math.ceil(width).toInt
    val h = math.ceil(height).toInt
    canvas.width = w
    canvas.height = h
    canvas.context.clearRect(0.0, 0.0, w, h)
    canvas
  }

  def restore(canvas: html.Canvas): Unit = synchronized {
    cached = canvas :: cached
  }
}