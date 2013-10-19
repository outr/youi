package org.hyperscala.web

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageHandler(pageCreator: () => Webpage, scope: Scope) extends HttpHandler {
  val id = Unique()

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

  def load(request: HttpRequest) = scope match {
    case Scope.Request => None
    case Scope.Page => request.url.parameters.getFirst("pageId") match {
      case Some(pageId) => Website().pages.get(pageId)
      case None => None
    }
    case Scope.Session => Website().pages.get(Website().session.id)
    case Scope.Application => Website().pages.get(id)
  }

  def cache(page: Webpage) = {
    scope match {
      case Scope.Request => // Nothing to do
      case Scope.Page => // Nothing to do
      case Scope.Session => Website().pages(Website().session.id) = page
      case Scope.Application => Website().pages(id) = page
    }
    Website().pages(page.pageId) = page         // All pages are stored at least by their id
  }
}

object WebpageHandler {
  def pageById[W <: Webpage](pageId: String) = Website().pages[W](pageId)
}