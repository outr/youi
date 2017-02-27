package io.youi.util

import org.scalajs.dom._

import scala.concurrent.{Future, Promise}

object ImageReloader {
  def reload(url: String): Future[Unit] = {
    val promise = Promise[Unit]()

    val iFrame = document.createElement("iframe").asInstanceOf[html.IFrame]
    var firstLoad = true
    iFrame.onload = (evt: Event) => {
      if (firstLoad) {
        firstLoad = false
        iFrame.contentWindow.location.reload(true)    // Force reload
      } else {
        promise.success(())
        document.body.removeChild(iFrame)
      }
    }
    iFrame.style.display = "none"
    iFrame.src = url
    document.body.appendChild(iFrame)

    promise.future
  }
}