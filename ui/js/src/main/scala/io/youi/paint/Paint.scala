package io.youi.paint

import io.youi.net.URL
import io.youi.{Color, Context}

import scala.scalajs._
import scala.scalajs.js.|

trait Paint {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def update(delta: Double): Unit = {}
  def modified: Long = 0L
  def asJS(context: Context): js.Any
}

object Paint {
  def none: Paint = NoPaint
  def color(color: Color): Paint = ColorPaint(color)
//  def horizontal(component: Component): LinearGradientPaint = horizontal(component.size.width)
  def horizontal(width: Double): LinearGradientPaint = linear(0.0, 0.0, width, 0.0)
//  def vertical(component: Component): LinearGradientPaint = vertical(component.size.height)
  def vertical(height: Double): LinearGradientPaint = linear(0.0, 0.0, 0.0, height)
  def linear(x0: Double, y0: Double, x1: Double, y1: Double): LinearGradientPaint = {
    LinearGradientPaint(x0, y0, x1, y1)
  }
  def radial(x0: Double, y0: Double, r0: Double, x1: Double, y1: Double, r1: Double): RadialGradientPaint = {
    RadialGradientPaint(x0, y0, r0, x1, y1, r1)
  }
//  def component[C <: Component](component: C, repetition: Repetition = Repetition.Repeat): ComponentPaint[C] = {
//    new ComponentPaint(component, repetition)
//  }

//  def image(url: String | URL,
//            repetition: Repetition = Repetition.Repeat,
//            mode: ImageMode = ImageMode.Quality): ComponentPaint[ImageView] = {
//    val image = new ImageView
//    image.load(url.toString, mode)
//    component(image, repetition)
//  }

//  def video(url: String | URL,
//            repetition: Repetition = Repetition.Repeat,
//            autoPlay: Boolean = true,
//            loop: Boolean = true,
//            muted: Boolean = true): ComponentPaint[Video] = {
//    val video: Video = new Video(url.toString)
//    video.autoPlay := autoPlay
//    video.loop := loop
//    video.muted := muted
//    component(video, repetition)
//  }
}