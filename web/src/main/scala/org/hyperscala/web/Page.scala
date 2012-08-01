package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page {
  protected[web] def disposed: Boolean

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
