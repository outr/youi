package org.hyperscala.web

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.powerscala.log.Logging
import com.outr.net.http.handler.HandlerProcessor
import org.powerscala.event.Listenable
import org.powerscala.Unique
import com.outr.net.http.session.Session
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageHandler[S <: Session](pageCreator: () => Webpage[S],
                                   val scope: Scope,
                                   val website: Website[S],
                                   val uris: List[String]) extends HttpHandler with Logging with Listenable {
  /**
   * Allows pre-management of the response before handling by a webpage. If the HttpResponseStatus is not NotFound then
   * the page will not be referenced to handle it.
   */
  val handlers = new HandlerProcessor()
  /**
   * If set to true, only pre-existing pages will be used referenced by pageId.
   *
   * Defaults to false.
   */
  val onlyById = Property[Boolean](default = Some(false))

  val id = Unique()

  def link = uris.headOption.getOrElse(null)

  final def onReceive(request: HttpRequest, response: HttpResponse) = try {
    val updatedResponse = handlers.fire(request -> response)
    if (updatedResponse.status == HttpResponseStatus.NotFound) {
      handle(request, updatedResponse)
    } else {
      updatedResponse
    }
  } catch {
    case t: Throwable => {
      website.errorThrown(t)
      website.errorPage(request, response)
    }
  }

  def handle(request: HttpRequest, response: HttpResponse) = {
    val pageOption = load(request) match {
      case Some(p) => Some(p)                        // Found page cached, just return it
      case None if !onlyById() => {                  // Page not found, create and cache it
        val p = pageCreator()
        cache(p)
        Some(p)
      }
      case _ => None
    }
    pageOption match {
      case Some(page) => WebpageHandler.handle(request, response, page)
      case None => response
    }
  }

  def load(request: HttpRequest): Option[Webpage[S]] = scope match {
    case Scope.Request => None
    case Scope.Page => request.url.parameters.getFirst("pageId") match {
      case Some(pageId) => website._pages.get(pageId)
      case None => None
    }
    case Scope.Session => website._pages.get(sessionId)
    case Scope.Application => website._pages.get(id)
  }

  def cache(page: Webpage[S]) = {
    scope match {
      case Scope.Request => // Nothing to do
      case Scope.Page => // Nothing to do
      case Scope.Session => website._pages(sessionId) = page
      case Scope.Application => website._pages(id) = page
    }
    debug(s"Caching page: ${page.pageId}!")
    WebpageHandler.cachePage(page)         // All pages are stored at least by their id
  }

  private def sessionId = s"$id.${website.session.id}"
}

object WebpageHandler {
  def pageById[W <: Webpage[S], S <: Session](pageId: String, website: Website[S]) = website._pages.get[W](pageId)

  def cachePage[S <: Session](page: Webpage[S]) = page.website._pages(page.pageId) = page

  def handle[S <: Session](request: HttpRequest, response: HttpResponse, page: Webpage[S]) = {
    page.checkIn()
    page.onReceive(request, response)
  }
}