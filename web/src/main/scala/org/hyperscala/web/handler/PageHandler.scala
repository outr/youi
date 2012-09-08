package org.hyperscala.web.handler

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.Unique
import org.hyperscala.html.attributes.Method
import org.hyperscala.web.{Website, Scope, Page}
import org.hyperscala.web.session.Session
import org.powerscala.bus.Bus

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PageHandler(val link: String, scope: Scope = Scope.Request, matcher: String => Boolean = null)
                 (instantiator: () => Page)(implicit website: Website[_ <: Session]) extends ContentHandler {
  def matches(uri: String) = if (matcher != null) {
    matcher(uri)
  } else {
    uri.equalsIgnoreCase(link)
  }

  lazy val uniqueName = Unique()

  website.contents += this

  // Automatically add itself to the website

  /**
   * Returns the page this handler wraps. This will create a new instance if necessary or return a cached copy if the
   * scope is set as such.
   */
  def page(method: Method, request: HttpServletRequest) = {
    try {
      val storage = scope match {
        case Scope.Application => website.application
        case Scope.Session | Scope.Page => website.session
        case _ => null
      }
      val lookup = if (storage != null) {
        storage.get[Page](uniqueName)
      } else {
        None
      }
      val p = lookup match {
        case Some(pg) if (!disposed(pg, scope, method, request)) => pg // Found page in storage
        case _ => createPage()
      }
      if (storage != null) {
        storage(uniqueName) = p // Store the page to the storage session if one exists
      }
      p
    } finally {
      Bus.current = null
    }
  }

  private def disposed(page: Page, scope: Scope, method: Method, request: HttpServletRequest) = {
    if (page.isDisposed) {
      true
    } else if (page.shouldDispose(scope, method, request)) {
      page.dispose()
      true
    } else {
      false
    }
  }

  /**
   * Create a new instance of the page
   */
  def createPage() = {
    instantiator()
  }

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    try {
      val page = this.page(method, request)

      Bus.current = page.bus
      try {
        page.service(method, request, response)
      } finally {
        Bus.current = null
      }
      if (scope == Scope.Request) {
        page.dispose()      // TODO: support other scopes for disposal
      }
    } catch {
      case t: Throwable => website.errorOccurred(t)
    }
  }
}

object PageHandler {
  def uriMatcher(uri: String)(toMatch: String) = uri == toMatch || "/%s".format(uri) == toMatch

  def apply(link: String, scope: Scope = Scope.Request, matcher: (String => Boolean) = null)
           (instantiator: => Page)(implicit website: Website[_ <: Session]) = {
    new PageHandler(link, scope, matcher)(() => instantiator)(website)
  }
}