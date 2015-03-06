package org.hyperscala.web

import com.outr.net.http.HttpHandler
import com.outr.net.http.handler.HandlerProcessor
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.session.Session
import org.powerscala.Unique
import org.powerscala.event.Listenable
import org.powerscala.log.Logging
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

  def link = uris.headOption.orNull

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

  def load(request: HttpRequest): Option[Webpage[S]] = request.url.parameters.getFirst("pageId") match {
    case Some(pageId) => website.pages.byPageId[Webpage[S]](pageId)
    case None => scope match {
      case Scope.Request => None                                            // Always get a new page on new request
      case Scope.Page => None                                               // If no pageId is supplied, we have no way to find it
      case Scope.Session => website.pages.bySessionHandlerId(id)            // Referenced in session by handler id
      case Scope.Application => website.pages.byApplicationHandlerId(id)    // Referenced by handler id
    }
  }

  def cache(page: Webpage[S]) = {
    debug(s"Caching page: ${page.pageId} with Scope: $scope")
    WebpageHandler.cachePage(page, scope, Some(id))
  }
}

// TODO: get or create a new RequestScope (only for the page request, not subsequent requests), SessionScope, and ApplicationScope

object WebpageHandler {
  def pageById[W <: Webpage[S], S <: Session](pageId: String, website: Website[S]) = website.pages.byPageId[W](pageId)

  def cachePage[S <: Session](page: Webpage[S], scope: Scope, handlerId: Option[String]) = page.website.pages.add(page, scope, handlerId)

  def handle[S <: Session](request: HttpRequest, response: HttpResponse, page: Webpage[S]) = {
    page.checkIn()
    page.onReceive(request, response)
  }
}