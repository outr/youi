package org.hyperscala.site.service

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class POSTExampleService extends HttpServlet {
  override def doPost(request: HttpServletRequest, response: HttpServletResponse) = {
    val writer = response.getWriter
    try {
      response.setContentType("application/json")
      writer.write("{ \"message\": \"Hello %s!\" }".format(request.getParameter("name")))
    } finally {
      writer.flush()
      writer.close()
    }
  }
}
