package org.hyperscala.web

import org.hyperscala.html._
import org.hyperscala.module.ModularPage
import org.powerscala.concurrent.Temporal
import org.powerscala.concurrent.Time._
import org.powerscala.hierarchy.{MutableChildLike, ParentLike}
import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.{Page, Markup, Unique}
import org.powerscala.MapStorage
import java.io.OutputStream
import org.powerscala.hierarchy.event.{ChildRemovedProcessor, ChildAddedProcessor, StandardHierarchyEventProcessor}
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends HttpHandler with HTMLPage with ModularPage with Temporal with ParentLike[tag.HTML] {
  Webpage.updateContext(this)

  private val _rendered = new AtomicBoolean(false)
  def rendered = _rendered.get()

  val pageId = Unique()
  val store = new MapStorage[Any, Any]

  val childAdded = new ChildAddedProcessor
  val childRemoved = new ChildRemovedProcessor
  val pageLoadingEvent = new StandardHierarchyEventProcessor[Webpage]("pageLoading")
  val pageLoadedEvent = new StandardHierarchyEventProcessor[Webpage]("pageLoaded")

  val doctype = "<!DOCTYPE html>\r\n"
  private lazy val basicHTML = new tag.HTML

  def html = basicHTML

  def head = html.head
  def body = html.body

  MutableChildLike.assignParent(html, this)

  protected lazy val hierarchicalChildren = List(html)

  def onReceive(request: HttpRequest, response: HttpResponse) = {
    val status = HttpResponseStatus.OK
    val content = new HTMLStreamer(html) {
      override def stream(output: OutputStream) = {
        pageLoading()
        super.stream(output)
        pageLoaded()
      }
    }
    val headers = response.headers.CacheControl()
    response.copy(content = content, status = status, headers = headers)
    // TODO: error handling
  }

  /**
   * The amount of time in seconds this webpage will continue to be cached in memory without any communication.
   *
   * Defaults to 2 minutes.
   */
  def timeout = 2.minutes

  /**
   * Called before the page is (re)loaded.
   */
  def pageLoading() = {
    pageLoadingEvent.fire(this)
  }

  /**
   * Called after the page is (re)loaded.
   */
  def pageLoaded() = {
    html.byTag[HTMLTag].foreach(Markup.rendered)
    _rendered.set(true)
    pageLoadedEvent.fire(this)
  }

  /**
   * Invokes the supplied function on all matching tags immediately and invokes on all new tags created at init time.
   *
   * @param f the function to invoke
   * @param manifest defines the erasured generic type of the matching T
   * @tparam T the filtered tag type to apply to the function
   */
  def live[T <: HTMLTag](f: T => Unit)(implicit manifest: Manifest[T]) = {
    // First we walk through the hierarchical structure
    html.byTag[T](manifest).foreach {
      case t => f(t)
    }
    // Now we intercept init to determine when new items are created
    intercept.init.on {
      case m: Markup if m.getClass.hasType(manifest.runtimeClass) => f(m.asInstanceOf[T])
      case _ => // Ignore
    }
  }

  def dispose() = {
    Website().pages.remove(pageId)
  }
}

object Webpage {
  def apply() = Website().requestContext[Webpage]("webpage")

  def updateContext(webpage: Webpage) = {
    Page.instance.set(webpage)
    Website().requestContext("webpage") = webpage
  }
}