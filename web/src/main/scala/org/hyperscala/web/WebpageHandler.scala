package org.hyperscala.web

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageHandler(pageCreator: () => Webpage, scope: Scope, val uris: List[String]) extends HttpHandler {
  val id = Unique()

  def link = uris.head

  def onReceive(request: HttpRequest, response: HttpResponse) = if (response.status == HttpResponseStatus.NotFound) {
    val page = load(request) match {
      case Some(p) => p               // Found page cached, just return it
      case None => {                  // Page not found, create and cache it
        val p = pageCreator()
        cache(p)
        p
      }
    }
    page.onReceive(request, response)
  } else {
    response
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
    Website()._pages(page.pageId) = page         // All pages are stored at least by their id
  }

  private def sessionId = s"$id.${Website().session.id}"
}

object WebpageHandler {
  def pageById[W <: Webpage](pageId: String) = Website()._pages[W](pageId)
}