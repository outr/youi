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
   * further WebResourceInterceptors are ignored (before and after), WebResource.apply is not called, but for all
   * interceptors that have been invoked, the after method is guaranteed to be called before returning. Will not be
   * called if a WebResourceInterceptor before it returned false.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   * @return true if the processing should continue, false, if propagation should end immediately.
   */
  def before(method: Method, request: HttpServletRequest, response: HttpServletResponse): Boolean

  /**
   * Invoked after before, and after WebResource.apply if all WebResourceInterceptors returned true. Will not be called
   * if a WebResourceInterceptor before it returned false in the before method.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   */
  def after(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit
}
