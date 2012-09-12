package org.hyperscala.web.resource.interceptor

import org.powerscala.bus.Bus
import org.hyperscala.web.resource.WebResourceInterceptor
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BusInterceptor(bus: Bus) extends WebResourceInterceptor {
  def before(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    Bus.current = bus
    true
  }

  def after(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    Bus.current = null
  }
}
