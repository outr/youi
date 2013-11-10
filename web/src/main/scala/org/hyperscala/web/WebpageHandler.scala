package org.hyperscala.web

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.Unique
import org.powerscala.log.Logging
import com.outr.net.http.handler.HandlerProcessor
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageHandler(pageCreator: () => Webpage, scope: Scope, val uris: List[String]) extends HttpHandler with Logging with Listenable {
  /**
   * Allows pre-management of the response before handling by a webpage. If the HttpResponseStatus is not NotFound then
   * the page will not be referenced to handle it.
   */
  val handlers = new HandlerProcessor()

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
      Website().errorThrown(t)
      Website().errorPage(request, response)
    }
  }

  def handle(request: HttpRequest, response: HttpResponse) = {
    val page = load(request) match {
      case Some(p) => p               // Found page cached, just return it
      case None => {                  // Page not found, create and cache it
        val p = pageCreator()
        cache(p)
        p
      }
    }
    WebpageHandler.handle(request, response, page)
  }

  def load(request: HttpRequest): Option[Webpage] = scope match {
    case Scope.Request => None
    case Scope.Page => request.url.parameters.getFirst("pageId") match {
      case Some(pageId) => Website()._pages.get(pageId)
      case None => None
    }
    case Scope.Session => Website()._pages.get(sessionId)
    case Scope.Application => Website()._pages.get(id)
  }

  def cache(page: Webpage) = {
    scope match {
      case Scope.Request => // Nothing to do
      case Scope.Page => // Nothing to do
      case Scope.Session => Website()._pages(sessionId) = page
      case Scope.Application => Website()._pages(id) = page
    }
    debug(s"Caching page: ${page.pageId}!")
    Website()._pages(page.pageId) = page         // All pages are stored at least by their id
  }

  private def sessionId = s"$id.${Website().session.id}"
}

object WebpageHandler {
  def pageById[W <: Webpage](pageId: String) = Website()._pages.get[W](pageId)

  def handle(request: HttpRequest, response: HttpResponse, page: Webpage) = {
    page.checkIn()
    page.onReceive(request, response)
  }
}