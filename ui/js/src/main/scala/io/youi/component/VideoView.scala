package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import io.youi.theme.{ComponentTheme, VideoViewTheme}
import io.youi.video.Video
import org.scalajs.dom.html
import reactify._

class VideoView(override protected val element: html.Span = dom.create[html.Span]("span")) extends HTMLComponent[html.Span] {
  override lazy val theme: Var[_ <: ComponentTheme] = Var(VideoView)
  override def componentType: String = "VideoView"

  lazy val video: Var[Video] = connect(Var(Video.empty), None, setVideo)

  def play(): Unit = video.play()
  def pause(): Unit = video.pause()
  def isPaused: Boolean = video.isPaused
  def isEnded: Boolean = video.isEnded

  private def setVideo(video: Video): Unit = {
    // Clear existing elements
    while (element.hasChildNodes()) {
      element.removeChild(element.firstChild)
    }
    // Add video to element
    element.appendChild(video.element)
    // TODO: consider ramifications of the same video in multiple locations
  }

  override protected def measuredWidth: Double = video.width.toDouble

  override protected def measuredHeight: Double = video.height.toDouble
}

object VideoView extends VideoViewTheme