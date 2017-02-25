package io.youi.app.screen

import com.outr.reactify.ChangeListener
import io.youi.{History, HistoryStateChange}
import io.youi.net.URL

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait URLActivation extends Screen {
  History.url.changes(new ChangeListener[URL] {
    override def change(oldValue: URL, newValue: URL): Unit = validateURL(newValue)
  })
  validateURL(History.url())

  private def validateURL(url: URL): Unit = if (isURLMatch(url)) {
    if (state() == ScreenState.Activated) {
      urlChanged(url)
    } else {
      ScreenManager().activate(this)
    }
  } else {
    ScreenManager().deactivate(this)
  }

  def isURLMatch(url: URL): Boolean

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