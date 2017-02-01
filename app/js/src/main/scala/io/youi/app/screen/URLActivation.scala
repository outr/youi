package io.youi.app.screen

import io.youi.app.{History, HistoryStateChange}
import io.youi.net.URL

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait URLActivation {
  this: Screen =>

  History.url.distinct.attach { url =>
    if (isURLMatch(url)) {
      ScreenManager().activate(this)
    }
  }

  def isURLMatch(url: URL): Boolean

  def updateURL(current: URL): Option[HistoryStateChange]

  override protected def activate(): Future[Unit] = {
    this.activate().map { _ =>

      val currentURL = History.url()
      updateURL(currentURL).foreach { change =>
        History.update(change)
      }
    }
  }
}