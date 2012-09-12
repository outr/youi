package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * WebResourceInterceptor is utilized by InterceptableWebResource to provide similar functionality to servlet filters
 * but in a simpler syntax.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceInterceptor {
  /**
   * Invoked before the WebResource.apply method is invoked. If the method returns false all processing is stopped and
   * further WebResourceInterceptors are ignored (before and after) as well as WebResource.apply is not invoked for the
   * request.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   * @return true if the processing should continue, false, if propagation should end immediately.
   */
  def before(method: Method, request: HttpServletRequest, response: HttpServletResponse): Boolean

  /**
   * Invoked after the WebResource.apply method is invoked and only if all interceptor before methods return true.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   */
  def after(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
