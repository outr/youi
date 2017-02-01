package io.youi.app.screen

import io.youi.app.History
import io.youi.app.stream.StreamURL
import io.youi.dom
import io.youi.net.URL
import org.scalajs.dom.html.Element

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ContentScreen[E <: Element] extends Screen with URLActivation {
  protected def containerSelector: String
  protected def contentSelector: String
  protected def contentPath: String

  private def contentContainer: Element = dom.oneBySelector[Element](containerSelector)
  def content: Option[E] = dom.firstBySelector[E](contentSelector)

  override protected def load(): Future[Unit] = super.load().flatMap { _ =>
    if (content.isEmpty) {      // Content hasn't been loaded yet
      val url = History.url().replacePathAndParams(s"$contentPath?selector=${URL.encode(contentSelector)}")
      StreamURL.stream(url).map { html =>
        val content = dom.fromString[E](html).head
        contentContainer.appendChild(content)
      }
    } else {                    // Content has already been loaded either by page load or by previous load
      Future.successful(())
    }
  }
}