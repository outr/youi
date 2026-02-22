package io.youi.app.screen

import rapid.Task
import io.youi.dom._
import io.youi.stream.StreamURL
import io.youi.{History, dom}
import org.scalajs.dom.html
import reactify.{Val, Var}
import spice.http.HttpMethod
import spice.net.Parameters

trait ContentScreen extends Screen with PathActivation {
  private def pageTag: html.Element = dom.byTag[html.Element]("page").head

  private val contentOptionVar = Var[Option[html.Span]](None)
  protected val contentOption: Val[Option[html.Span]] = contentOptionVar
  protected def content: html.Span = contentOption().getOrElse(throw new RuntimeException("Content not set!"))

  private lazy val preloaded: Task[Unit] = {
    // On load check to see if the screen tag is loaded already
    dom.byTag[html.Element]("screen").headOption.foreach { screen =>
      loadScreen(screen)
    }

    if (contentOption().isEmpty) {      // Content hasn't been loaded yet
      generateScreen().map(loadScreen)
    } else {                          // Content has already been loaded either by page load or by previous load
      Task.unit
    }
  }

  override protected def load(): Task[Unit] = super.load().flatMap { _ =>
    preloaded
  }

  def preload(): Task[Unit] = preloaded

  protected def generateScreen(): Task[html.Element] = {
    val url = History
      .url()
      .copy(path = path, parameters = Parameters.empty)
      .withParam("part", "true")
      .withParam("selector", "screen")
    scribe.debug(s"Loading content $url...")
    StreamURL
      .stream(url, method = HttpMethod.Get)
      .map { html =>              // Fix for non-youi-server
        val start = html.indexOf("<screen>")
        val end = html.indexOf("</screen>")
        html.substring(start, end + 9)
      }
      .map { htmlString =>
        scribe.debug(s"Content loaded successfully (${htmlString.length} characters)")
        dom
          .fromString[html.Element](htmlString)
          .headOption
          .getOrElse(throw new RuntimeException(s"No content found in: [$htmlString] for URL: $url"))
      }
  }

  private def loadScreen(screen: html.Element): Unit = {
    val span = dom.create[html.Span]("span")
    screen.remove()
    val size = screen.childNodes.length
    (0 until size).map(screen.childNodes.item).foreach { child =>
      child match {
        case e: html.Element => e.remove()
        case _ => // Ignore nodes
      }
      span.appendChild(child)
    }

    hideContent(span)
    pageTag.appendChild(span)
    contentOptionVar @= Some(span)
  }

  protected def showContent(content: html.Element): Unit = {
    content.style.removeProperty("display")
  }

  protected def hideContent(content: html.Element): Unit = {
    content.style.display = "none"
  }

  override protected def activate(): Task[Unit] = super.activate().map { _ =>
    contentOption().foreach(showContent)
  }

  override protected def deactivate(): Task[Unit] = super.deactivate().map { _ =>
    contentOption().foreach(hideContent)
  }
}