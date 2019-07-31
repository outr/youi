package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom._
import io.youi.spatial.Size
import io.youi.theme.{Theme, VideoViewTheme}
import org.scalajs.dom.{document, html}

class VideoView(override protected val element: html.Element = create[html.Element]("span"),
                val existing: Boolean = false) extends HTMLComponent[html.Element] with VideoViewTheme {
  override protected def defaultParentTheme: Theme = VideoView

  override def componentType: String = "VideoView"

  def play(): Unit = video.play()
  def pause(): Unit = video.pause()
  def isPaused: Boolean = video.isPaused
  def isEnded: Boolean = video.isEnded

  visible.attach { b =>
    if (autoPauseOnHide() && !b) pause()
  }

  override protected def measure(s: Size): Size = s.set(video.width.toDouble, video.height.toDouble)
}

object VideoView extends VideoViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): VideoView = {
    new VideoView(in.byId[html.Element](id), existing = true)
  }
}