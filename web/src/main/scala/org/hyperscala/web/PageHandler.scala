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
        case Some(p) => p
        case None => {
          val p = instantiator()
          website(uniqueName) = p
          p
        }
      }
      case Scope.Session => website.session.get[Page](uniqueName) match {
        case Some(p) => p
        case None => {
          val p = instantiator()
          website.session(uniqueName)
          p
        }
      }
//      case Scope.Instance => // TODO: implement
      case Scope.Request => instantiator()
    }
    page.service(method, request, response)
  }
}