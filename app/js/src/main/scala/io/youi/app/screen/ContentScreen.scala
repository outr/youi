package io.youi.app.screen

import com.outr.reactify.{Val, Var}
import io.youi.app.History
import io.youi.app.stream.StreamURL
import io.youi.dom
import io.youi.dom._
import io.youi.net.URL
import org.scalajs.dom.html.Element

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ContentScreen[E <: Element] extends Screen with PathActivation {
  protected def containerSelector: String
  protected def contentSelector: String

  private def contentContainer: Element = dom.oneBySelector[Element](containerSelector)

  private val contentOptionVar = Var[Option[E]](None)
  protected val contentOption: Val[Option[E]] = Val(contentOptionVar)
  protected def content: E = contentOption().getOrElse(throw new RuntimeException("Content not set!"))

  override protected def load(): Future[Unit] = super.load().flatMap { _ =>
    if (contentOption.isEmpty) {      // Content hasn't been loaded yet
      val url = History.url().replacePathAndParams(path).withParam("part", "true").withParam("selector", contentSelector)
      StreamURL.stream(url).map { html =>
        val content = dom.fromString[E](html).headOption.getOrElse(throw new RuntimeException(s"No content found in: [$html] for URL: $url"))
        contentOptionVar := Some(content)
      }
    } else {                          // Content has already been loaded either by page load or by previous load
      Future.successful(())
    }
  }

  protected def showContent(): Unit = contentOption.foreach { c =>
    if (Option(c.parentElement).isEmpty) {
      contentContainer.appendChild(c)
    }
  }
  protected def hideContent(): Unit = contentOption.foreach { c =>
    if (Option(c.parentElement).nonEmpty) {
      c.remove()
    }
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    showContent()
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    hideContent()
  }
}