package io.youi.video

import scala.concurrent.{Future, Promise}

class Video(private[youi] val element: html.Video) extends Drawable {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty

  val width: Int = element.videoWidth
  val height: Int = element.videoHeight
  val duration: Double = element.duration

  val autoPlay: Var[Boolean] = Var(false)
  val loop: Var[Boolean] = Var(false)
  val muted: Var[Boolean] = Var(false)
  val position: Var[Double] = Var(0.0)
  val volume: Var[Double] = Var(1.0)

  private var updatingTime = false

  private def init(autoPlay: Boolean,
                   loop: Boolean,
                   muted: Boolean): Unit = if (!isEmpty) {
    this.autoPlay @= autoPlay
    this.loop @= loop
    this.muted @= muted

    this.autoPlay.attach(element.autoplay = _)
    this.loop.attach(element.loop = _)
    this.muted.attach(element.muted = _)

    position @= element.currentTime
    position.attach { p =>
      if (!updatingTime) element.currentTime = p
    }
    element.addEventListener("timeupdate", (_: Event) => {
      modified @= System.currentTimeMillis()
      updatingTime = true
      try {
        position @= element.currentTime
      } finally {
        updatingTime = false
      }
    })
    element.addEventListener("seeked", (_: Event) => {
      updatingTime = true
      try {
        position @= element.currentTime
      } finally {
        updatingTime = false
      }
    })

    var updatingVolume = false
    volume @= element.volume
    volume.attach { v =>
      if (!updatingVolume) element.volume = v
    }
    element.addEventListener("volumechange", (_: Event) => {
      updatingVolume = true
      try {
        volume @= element.volume
      } finally {
        updatingVolume = false
      }
    })
    modified @= System.currentTimeMillis()
  }

  def play(): Unit = element.play()
  def pause(): Unit = element.pause()
  def isPaused: Boolean = element.paused
  def isEnded: Boolean = element.ended

  def seek(position: Double): Future[Unit] = if (this.position() != position) {
    val promise = Promise[Unit]
    this.position.once(_ => {
      promise.success(())
    }, d => math.abs(position - d) <= 1.0)
    this.position @= position
    promise.future
  } else {
    Future.successful(())
  }

  def createImage(): Image = {
    val canvas = CanvasPool(width, height)
    val context = canvas.context
    context.drawImage(element, 0.0, 0.0)
    CanvasImage(canvas, ImageResizer.Pica)
  }

  override def draw(context: Context, x: Double, y: Double): Unit = if (!isEmpty) {
    context.drawVideo(element)(x, y, width, height)
    if (position() > 0.0 && !isPaused && !isEnded) {
      updatingTime = true
      try {
        position @= element.currentTime
      } finally {
        updatingTime = false
      }
      modified @= System.currentTimeMillis()
    }
  }

  def dispose(): Unit = pause()
}

object Video {
  object empty extends Video(dom.create[html.Video]("video")) {
    override def isEmpty: Boolean = true
  }

  def isVideo(file: File): Boolean = file.`type`.startsWith("video/")

  def apply(file: File, autoPlay: Boolean, loop: Boolean, muted: Boolean): Future[Video] = {
    val url = org.scalajs.dom.URL.createObjectURL(file)
    apply(url, autoPlay, loop, muted)
  }

  def apply(url: URL, autoPlay: Boolean, loop: Boolean, muted: Boolean): Future[Video] = {
    apply(url.toString, autoPlay, loop, muted)
  }

  def apply(url: String,
            autoPlay: Boolean,
            loop: Boolean,
            muted: Boolean): Future[Video] = {
    val element: html.Video = dom.create[html.Video]("video")
    element.autoplay = autoPlay
    element.loop = loop
    element.muted = muted
    val promise = Promise[Video]
    element.addEventListener("loadedmetadata", (_: Event) => {
      val v = new Video(element)
      v.init(autoPlay, loop, muted)
      promise.success(v)
    })
    element.src = url
    promise.future
  }
}