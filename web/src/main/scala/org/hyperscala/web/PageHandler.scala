package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import session.Session

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PageHandler(instantiator: () => Page, matcher: String => Boolean, scope: Scope = Scope.Request)
                 (implicit website: Website[_ <: Session]) extends ContentHandler {
  if (scope == Scope.Instance) throw new RuntimeException("Unsupported Instance Scope!!!")    // TODO: add support for Scope.Instance

  def matches(uri: String) = matcher(uri)

  lazy val uniqueName = getClass.getName

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val page = scope match {
      case Scope.Application => website.get[Page](uniqueName) match {
        case Some(p) if (!p.disposed) => p
        case _ => {
          val p = instantiator()
          website(uniqueName) = p
          p
        }
      }
      case Scope.Session => website.session.get[Page](uniqueName) match {
        case Some(p) if (!p.disposed) => p
        case _ => {
          val p = instantiator()
          website.session(uniqueName) = p
          p
        }
      }
//      case Scope.Instance => // TODO: implement
      case Scope.Request => instantiator()
    }
    page.service(method, request, response)
  }
}

object PageHandler {
  def uriMatcher(uri: String)(toMatch: String) = uri == toMatch || "/%s".format(uri) == toMatch

  def apply(uri: String, scope: Scope = Scope.Request)(f: => Page)(implicit website: Website[_ <: Session]) = {
    new PageHandler(() => f, uriMatcher(uri) _, scope)(website)
  }
}