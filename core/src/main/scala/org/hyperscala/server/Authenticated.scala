package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Authenticated[S <: Session] extends Service[S] {
  def isAuthenticated(session: S): Boolean

  abstract override def invoke(session: S, request: HttpServletRequest, response: HttpServletResponse) = {
    if (isAuthenticated(session)) {
      super.invoke(session, request, response)
    } else {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.")
    }
  }
}
