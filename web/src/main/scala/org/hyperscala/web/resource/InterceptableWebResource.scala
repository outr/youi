package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * InterceptableWebResource can be mixed into WebResources to allow servlet filter-like actions to occur before and
 * after execution. Overriding createInterceptors allows a list of WebResourceInterceptors to be defined for processing.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait InterceptableWebResource extends WebResource {
  /**
   * Lazily loaded interceptors from createInterceptors method.
   */
  lazy val interceptors = createInterceptors()

  /**
   * Creates the list of WebResourceInterceptors to be used when this WebResource is invoked.
   */
  protected def createInterceptors(): List[WebResourceInterceptor] = Nil

  override def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) {
    processInterceptors(interceptors.toList, method, request, response)
  }

  private def processInterceptors(list: List[WebResourceInterceptor], method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    if (list.isEmpty) {
      super.service(method, request, response)
    } else {
      val interceptor = list.head
      try {
        if (interceptor.before(method, request, response)) {
          processInterceptors(list.tail, method, request, response)
        }
      } finally {
        interceptor.after(method, request, response)
      }
    }
  }
}
