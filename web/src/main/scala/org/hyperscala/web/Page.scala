package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.html.attributes.Method

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page {
  protected[web] def disposed: Boolean

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
