package org.hyperscala.web.resource.handler

import org.powerscala.Priority
import org.powerscala.hierarchy.Element
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * WebResourceHandler is the base class to deliver WebResources based on URI lookups.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceHandler extends Element {
  /**
   * Process the request and return true if handled.
   */
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse): Boolean

  /**
   * Sorting priority of the handler among other handlers in the site.
   *
   * Defaults to Priority.Normal
   */
  def priority: Priority = Priority.Normal
}