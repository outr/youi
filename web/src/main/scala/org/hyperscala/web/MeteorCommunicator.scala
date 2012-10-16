package org.hyperscala.web

import org.atmosphere.config.service.MeteorService
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor
import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import org.atmosphere.cpr._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
@MeteorService(path = "/*", interceptors = Array(classOf[AtmosphereResourceLifecycleInterceptor]))
class MeteorCommunicator extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val meteor = Meteor.build(request).addListener(new AtmosphereResourceEventListenerAdapter)
    meteor.
    meteor.resumeOnBroadcast(meteor.transport() == AtmosphereResource.TRANSPORT.LONG_POLLING).suspend(-1)
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse) {
    val body = request.getReader.readLine().trim
    val author = body.substring(body.indexOf(":") + 2, body.indexOf(",") - 1)
    val message = body.substring(body.lastIndexOf(":") + 2, body.length - 2)
    BroadcasterFactory.getDefault.lookup(classOf[DefaultBroadcaster], "/*").broadcast(new )
  }
}
