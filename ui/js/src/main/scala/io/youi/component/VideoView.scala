package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import io.youi.theme.{Theme, VideoViewTheme}
import org.scalajs.dom.html

class VideoView(override protected val element: html.Span = dom.create[html.Span]("span")) extends HTMLComponent[html.Span] with VideoViewTheme {
  override protected def defaultParentTheme: Theme = VideoView

  override def componentType: String = "VideoView"

  def play(): Unit = video.play()
  def pause(): Unit = video.pause()
  def isPaused: Boolean = video.isPaused
  def isEnded: Boolean = video.isEnded

  visible.attach { b =>
    if (autoPauseOnHide() && !b) pause()
  }

  override protected def measuredWidth: Double = video.width.toDouble

  override protected def measuredHeight: Double = video.height.toDouble
}

object VideoView extends VideoViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent
}