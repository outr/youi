package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResource {
  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    apply(method, request, response)
  }

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}