package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * WebResource is the object representation of a URI accessed via the web.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResource {
  // TODO: refactor to remove request and response and create WebRequest that contains access to POST data auto-converted
  // to JSON, XML, or parameters along with items off the GET request and ability to stream data back out.

  /**
   * This method is invoked by the Website upon receipt of this object via a WebResourceHandler.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   */
  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    apply(method, request, response)
  }

  /**
   * Called by the service method and is responsible for the main work of the resource.
   *
   * @param method the HTTP method this resource was hit with
   * @param request the servlet request
   * @param response the servlet response
   */
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit

  /**
   * @return true if this WebResource has been disposed - defaults to false
   */
  def disposed: Boolean = false
}