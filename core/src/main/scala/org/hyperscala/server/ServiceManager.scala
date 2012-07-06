package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ServiceManager[S <: Session] extends HttpServlet {
  private var services = List.empty[Service[_, _]]

  def createSession(): S

  def session(req: HttpServletRequest) = {
    val sessionKey = "org.hyperscala.server.Session"
    val httpSession = req.getSession
    httpSession.getAttribute(sessionKey) match {
      case null => {
        val session = createSession()
        httpSession.setAttribute(sessionKey, session)
        session
      }
      case s => s.asInstanceOf[S]
    }
  }

  override def service(req: HttpServletRequest, resp: HttpServletResponse) {
    // Lookup service

  }

  def register(service: Service[_, _]) = synchronized {
    services = (service :: services.reverse).reverse
  }
}
