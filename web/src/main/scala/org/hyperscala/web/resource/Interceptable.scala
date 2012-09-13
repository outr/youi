package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Interceptable {
  /**
   * Lazily loaded interceptors from createInterceptors method.
   */
  lazy val interceptors = createInterceptors()

  /**
   * Creates the list of WebResourceInterceptors to be used when this WebResource is invoked.
   */
  protected def createInterceptors(): List[WebResourceInterceptor] = Nil

  def processInterceptors(list: List[WebResourceInterceptor], method: Method, request: HttpServletRequest, response: HttpServletResponse)(f: () => Unit): Boolean = {
    if (list.isEmpty) {
      f()
      true
    } else {
      val interceptor = list.head
      try {
        if (interceptor.before(method, request, response)) {
          processInterceptors(list.tail, method, request, response)(f)
        } else {
          false
        }
      } finally {
        interceptor.after(method, request, response)
      }
    }
  }
}