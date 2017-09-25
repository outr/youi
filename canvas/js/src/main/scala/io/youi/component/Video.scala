package io.youi.component

import io.youi.net.URL
import io.youi.theme.VideoTheme
import io.youi.{Context, History, dom}
import org.scalajs.dom.{Event, html}
import reactify.Var

class Video extends Component with VideoTheme {
  private lazy val element = {
    val v = dom.create[html.Video]("video")
    autoPlay.attachAndFire(v.autoplay = _)
    loop.attachAndFire(v.loop = _)
    muted.attachAndFire(v.muted = _)
    v.addEventListener("loadedmetadata", (_: Event) => {
      updateMeasured(v.videoWidth, v.videoHeight)
    })
    v
  }

  override lazy val theme: Var[VideoTheme] = Var(Video)

  override protected def defaultThemeParent = Some(theme)

  def this(url: URL) = {
    this()

    element.src = url.toString
    element.load()
  }

  def this(path: String) = {
    this(History.url().withPath(path))
  }

  def play(): Unit = element.play()
  def pause(): Unit = element.pause()
  def isPaused: Boolean = element.paused
  def isEnded: Boolean = element.ended

  override def update(delta: Double): Unit = {
    if (!isPaused && !isEnded) {
      reDraw.flag()
    }

    super.update(delta)
  }

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (size.width() > 0.0 && size.height() > 0.0 && !element.paused && !element.ended && !element.seeking) {
      context.drawVideo(element)(width = size.width(), height = size.height())
    }
  }
}

object Video extends VideoTheme