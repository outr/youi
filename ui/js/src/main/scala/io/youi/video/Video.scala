package io.youi.video

import io.youi.dom
import io.youi.drawable.{Context, Drawable}
import io.youi.net.URL
import org.scalajs.dom.{Event, html}
import reactify._

import scala.concurrent.{Future, Promise}

class Video(val url: URL, element: html.Video) extends Drawable {
  val width: Int = element.videoWidth
  val height: Int = element.videoHeight
  val duration: Double = element.duration

  val autoPlay: Var[Boolean] = Var(false)
  val loop: Var[Boolean] = Var(false)
  val muted: Var[Boolean] = Var(false)
  val position: Var[Double] = Var(0.0)
  val volume: Var[Double] = Var(1.0)

  private def init(autoPlay: Boolean,
                   loop: Boolean,
                   muted: Boolean): Unit = {
    this.autoPlay := autoPlay
    this.loop := loop
    this.muted := muted

    this.autoPlay.attach(element.autoplay = _)
    this.loop.attach(element.loop = _)
    this.muted.attach(element.muted = _)

    var updatingTime = false
    position := element.currentTime
    position.attach { p =>
      if (!updatingTime) element.currentTime = p
    }
    element.addEventListener("timeupdate", (_: Event) => {
      modified := System.currentTimeMillis()
      updatingTime = true
      try {
        position := element.currentTime
      } finally {
        updatingTime = false
      }
    })

    var updatingVolume = false
    volume := element.volume
    volume.attach { v =>
      if (!updatingVolume) element.volume = v
    }
    element.addEventListener("volumechange", (_: Event) => {
      updatingVolume = true
      try {
        volume := element.volume
      } finally {
        updatingVolume = false
      }
    })
    modified := System.currentTimeMillis()
  }

  def play(): Unit = element.play()
  def pause(): Unit = element.pause()
  def isPaused: Boolean = element.paused
  def isEnded: Boolean = element.ended

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.drawVideo(element)(x, y, width, height)
  }

  def dispose(): Unit = pause()
}

object Video {
  def apply(url: URL,
            autoPlay: Boolean = false,
            loop: Boolean = false,
            muted: Boolean = false): Future[Video] = {
    val element: html.Video = dom.create[html.Video]("video")
    element.autoplay = autoPlay
    element.loop = loop
    element.muted = muted
    val promise = Promise[Video]
    element.addEventListener("loadedmetadata", (_: Event) => {
      val v = new Video(url, element)
      v.init(autoPlay, loop, muted)
      promise.success(v)
    })
    element.src = url.toString
    promise.future
  }
}