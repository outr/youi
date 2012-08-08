package org.hyperscala.web.handler

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.Unique
import org.hyperscala.html.attributes.Method
import org.hyperscala.web.{Website, Scope, Page}
import org.hyperscala.web.session.Session

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PageHandler(val link: String, instantiator: () => Page, matchers: List[String => Boolean], scope: Scope = Scope.Request)
                 (implicit website: Website[_ <: Session]) extends ContentHandler {
  if (scope == Scope.Instance) throw new RuntimeException("Unsupported Instance Scope!!!")

  // TODO: add support for Scope.Instance

  def matches(uri: String) = matchers.find(matcher => matcher(uri)) != None

  lazy val uniqueName = Unique()

  website.contents += this

  // Automatically add itself to the website

  /**
   * Returns the page this handler wraps. This will create a new instance if necessary or return a cached copy if the
   * scope is set as such.
   */
  def page = {
    val storage = scope match {
      case Scope.Application => website
      case Scope.Session => website.session
      case _ => null
    }
    val lookup = if (storage != null) {
      storage.get[Page](uniqueName)
    } else {
      None
    }
    val p = lookup match {
      case Some(pg) if (!pg.disposed) => pg // Found page in storage
      case None => instantiator() // Create a new instance of the page
    }
    if (storage != null) {
      storage(uniqueName) = p // Store the page to the storage session if one exists
    }
    p
  }

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    page.service(method, request, response)
  }
}

object PageHandler {
  def uriMatcher(uri: String)(toMatch: String) = uri == toMatch || "/%s".format(uri) == toMatch

  def apply(uri: String, scope: Scope = Scope.Request, additionalURIs: List[String] = Nil)
           (f: => Page)(implicit website: Website[_ <: Session]) = {
    val matchers = (uri :: additionalURIs).map(s => uriMatcher(s) _)
    new PageHandler(uri, () => f, matchers, scope)(website)
  }
}