package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RedirectResource(uri: String) extends WebResource {
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = response.sendRedirect(uri)
}