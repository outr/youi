package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page {
  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
