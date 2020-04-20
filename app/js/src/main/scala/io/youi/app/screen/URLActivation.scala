package io.youi.app.screen

import io.youi.net.{URL, URLMatcher}
import io.youi.{History, HistoryStateChange}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait URLActivation extends Screen {
  History.url.attach(validateURL)
  validateURL(History.url())

  private def validateURL(url: URL): Unit = if (matcher.matches(url)) {
    if (state() == ScreenState.Activated) {
      urlChanged(url)
    } else {
      ScreenManager().active @= this
    }
  }

  def matcher: URLMatcher

  def updateURL(current: URL): Option[HistoryStateChange]

  /**
    * Called when the screen is already activated, but the URL has changed and is still a match.
    *
    * @param url the new URL
    */
  def urlChanged(url: URL): Unit = {}

  override protected def activate(): Future[Unit] = {
    super.activate().map { _ =>
      val currentURL = History.url()
      updateURL(currentURL).foreach { change =>
        History.update(change)
      }
    }
  }
}