package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceInterceptor extends WebResource {
  def before(method: Method, request: HttpServletRequest, response: HttpServletResponse): Boolean

  def after(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
