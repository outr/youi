package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.ServletConfig

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class ServiceManager[S <: Session](implicit manifest: Manifest[S]) extends HttpServlet {
  private var services = List.empty[Service[S]]

  override def init(config: ServletConfig) = {
    super.init(config)
    config.getInitParameter("services") match {
      case null => // Nothing to do
      case s => s.split(",").foreach(s => register(Class.forName(s.trim).newInstance().asInstanceOf[Service[S]]))
    }
  }

  def createSession(): S

  def loadSession(req: HttpServletRequest) = {
    val sessionKey = manifest.erasure.getName
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
    val uri = req.getRequestURI
    val session = loadSession(req)
    services.find(s => s.matches(uri)) match {
      case Some(service) => {
        service(session, req, resp)
//        val authenticated = service match {
//          case auth: Authenticated[_] => auth.asInstanceOf[Authenticated[S]].isAuthenticated(session)
//          case _ => true
//        }
//        if (authenticated) {
//          service.before(session)
//          service(session, req, resp)
//          service.after(session)
//        } else {
//          resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.")
//        }
      }
      case None => {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested page [%s] was not found.".format(uri))
      }
    }
  }

  def register(service: Service[S]) = synchronized {
    services = (service :: services.reverse).reverse
  }
}

class BasicServiceManager extends ServiceManager[Session] {
  def createSession() = new Session
}