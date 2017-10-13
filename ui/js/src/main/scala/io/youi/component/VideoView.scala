package io.youi.component

import io.youi.Modifiable
import io.youi.drawable.Context
import io.youi.theme.VideoViewTheme
import io.youi.video.Video
import reactify._

class VideoView extends Component with VideoViewTheme {
  lazy val video: Var[Video] = Var(Video.empty)

  override lazy val theme: Var[VideoViewTheme] = Var(VideoView)

  override protected def defaultThemeParent = Some(theme)

  override def `type`: String = "VideoView"

  override protected def init(): Unit = {
    super.init()

    updateMeasured(video.width, video.height)
  }

  override protected def modifiables: List[Modifiable] = super.modifiables ::: List(video())

  def play(): Unit = video.play()
  def pause(): Unit = video.pause()
  def isPaused: Boolean = video.isPaused
  def isEnded: Boolean = video.isEnded

  override protected def drawInternal(context: Context): Unit = if (video.nonEmpty) {
    video.draw(context, 0.0, 0.0)
  }

  init()
}

object VideoView extends VideoViewTheme