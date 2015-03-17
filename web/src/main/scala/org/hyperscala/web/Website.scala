package org.hyperscala.web

import com.outr.net.http.WebApplication
import com.outr.net.http.content.{ContentType, StringContent}
import com.outr.net.http.handler.CachedHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponse, HttpResponseStatus}
import com.outr.net.http.session.Session
import org.powerscala.hierarchy.AbstractMutableContainer
import org.powerscala.{Unique, MapStorage}
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.log.Logging
import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Website extends WebApplication with Logging with AbstractMutableContainer[Webpage] {
  implicit def childManifest: Manifest[Webpage] = Website.webpageManifest

  /**
   * Unique id for this Website instance. Can be used to lookup the Website.
   */
  val id = Unique()

  Website.register(this)      // Make sure the site is registered in the list

  /**
   * Application stores content that should be persistent throughout the life of the website.
   */
  val application = new MapStorage[Any, Any]
  /**
   * Invoked when a page throws an error.
   */
  val pageError = new UnitProcessor[(Webpage, Throwable)]("pageError")
  /**
   * Access to all currently loaded pages.
   */
  lazy val pages = new Pages(this)

  def init(): Unit = {
    handlers += CachedHandler               // Add caching support
    handlers += new ErrorHandler(this)      // Add error handling support - calls errorPage if there's an error with no content
    pageError.on {                          // Default logging of errors on a page
      case (page, t) => error(s"An exception was thrown on ${request.url} (page ${page.getClass.getSimpleName}).", t)
    }
  }

  def page(creator: => Webpage, scope: Scope, uris: String*) = {
    val handler = new WebpageHandler(() => creator, scope, this, uris.toList)
    if (uris.nonEmpty) {
      addHandler(handler, uris: _*)
    }
    handler
  }

  def errorThrown(t: Throwable) = {
    error(s"Error occurred on URL: ${request.url}.", t)
  }

  def errorPage(request: HttpRequest,
                response: HttpResponse,
                status: HttpResponseStatus = HttpResponseStatus.InternalServerError): HttpResponse = {
    val html = s"<html><head><title>${status.code} ${status.message}</title></head><body><h1><b>${status.code}</b>: ${status.message}</h1></body></html>"
    response.copy(status = status, content = StringContent(html, contentType = ContentType.HTML))
  }

  override def dispose() = {
    super.dispose()

    application.clear()         // Clear out the application storage at dispose time
    Website.unregister(this)    // Remove from the registry
  }

  override def update(delta: Double) = {
    super.update(delta)

    try {
      pages.foreach {
        case page => page.update(delta)
      }
      pages.byScope(Scope.Application).foreach(_.checkIn())     // Keep Application scoped elements from timing out
    } catch {
      case t: Throwable => error("Exception thrown while updating pages.", t)
    }
  }

  override protected[web] def addChild(child: Webpage): Unit = super.addChild(child)
  override protected[web] def removeChild(child: Webpage): Unit = super.removeChild(child)
}

object Website {
  private val webpageManifest = implicitly[Manifest[Webpage]]

  private var _sites = Map.empty[String, Website]

  private def register(website: Website) = synchronized {
    _sites += website.id -> website.asInstanceOf[Website]
  }
  private def unregister(website: Website) = synchronized {
    _sites -= website.id
  }

  def get(id: String) = _sites.get(id)
  def apply(id: String) = get(id).getOrElse(throw new RuntimeException(s"Unable to find website by id: $id."))
  def sites = _sites.values
}