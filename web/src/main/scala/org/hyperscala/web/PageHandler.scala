package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import session.Session
import org.hyperscala.Unique

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PageHandler(val link: String, instantiator: () => Page, matchers: List[String => Boolean], scope: Scope = Scope.Request)
                 (implicit website: Website[_ <: Session]) extends ContentHandler {
  if (scope == Scope.Instance) throw new RuntimeException("Unsupported Instance Scope!!!")    // TODO: add support for Scope.Instance

  def matches(uri: String) = matchers.find(matcher => matcher(uri)) != None

  lazy val uniqueName = Unique()

  website.contents += this      // Automatically add itself to the website

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
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
    val page = lookup match {
      case Some(p) if (!p.disposed) => p      // Found page in storage
      case None => instantiator()             // Create a new instance of the page
    }
    if (storage != null) {
      storage(uniqueName) = page        // Store the page to the storage session if one exists
    }
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