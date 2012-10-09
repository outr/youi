package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * InterceptableWebResource can be mixed into WebResources to allow servlet filter-like actions to occur before and
 * after execution. Overriding createInterceptors allows a list of WebResourceInterceptors to be defined for processing.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait InterceptableWebResource extends WebResource with WebResourceInterceptable {
  override def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) {
    processInterceptors(webResourceInterceptors.toList, method, request, response)(() => {
      super.service(method, request, response)
    })
  }
}
