package io.youi.video

import io.youi.{History, dom}
import io.youi.drawable.{Context, Drawable}
import io.youi.net.URL
import org.scalajs.dom.{Event, html}

import scala.concurrent.{Future, Promise}

class Video(val url: URL, element: html.Video) extends Drawable {
  val width: Int = element.videoWidth
  val height: Int = element.videoHeight

  def pause(): Unit = element.pause()

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.drawVideo(element)(x, y, width, height)
  }

  def dispose(): Unit = pause()
}

object Video {
  def apply(url: URL): Future[Video] = {
    val element: html.Video = dom.create[html.Video]("video")
    val promise = Promise[Video]
    element.addEventListener("loadedmetadata", (_: Event) => {
      promise.success(new Video(url, element))
    })
    element.src = url.toString
    promise.future
  }

  def apply(path: String): Future[Video] = apply(History.url().withPath(path))
}