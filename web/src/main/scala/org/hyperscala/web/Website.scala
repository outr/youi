package org.hyperscala.web

import org.powerscala.property.{Property, PropertyParent}
import org.powerscala.hierarchy.{ContainerView, MutableContainer}
import javax.servlet.ServletConfig
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import session.Session

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Website[S <: Session] extends MutableContainer[ContentHandler] with PropertyParent with Session {
  implicit val thisWebsite = this

  private var application = Map.empty[String, Any]
  private val _session = new ThreadLocal[S]

  val handlers = new ContainerView[ContentHandler](this, null, Website.prioritySort)

  val name = Property[String]("name", null)
  // TODO: error page support
  // TODO: add configuration options for caching static content

  contents += ResourceHandler

  def reload(config: ServletConfig) = {
    name := config.getServletContext.getServletContextName      // Load the web application name
  }

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val uri = request.getRequestURI
    _session.set(loadSession(request))
    try {
      lookupHandler(uri) match {
        case Some(handler) => handler(method, request, response)
        case None => response.sendError(HttpServletResponse.SC_NOT_FOUND, "The page could not be found: %s".format(uri))
      }
    } finally {
      _session.set(null.asInstanceOf[S])
    }
  }

  def session: S = _session.get()

  protected def lookupHandler(uri: String) = handlers.find(ch => ch.matches(uri))

  protected def loadSession(request: HttpServletRequest) = {
    val sessionKey = classOf[Session].getName
    val httpSession = request.getSession
    httpSession.getAttribute(sessionKey) match {
      case null => {
        val session = createSession
        httpSession.setAttribute(sessionKey, session)
        session
      }
      case session => session.asInstanceOf[S]
    }
  }

  protected def createSession: S

  // Session functionality

  def apply[T](name: String) = application(name).asInstanceOf[T]

  def get[T](name: String) = application.get(name).asInstanceOf[Option[T]]

  def update(name: String, value: Any) = application += name -> value
}

object Website {
  val prioritySort = (ch1: ContentHandler, ch2: ContentHandler) => -ch1.priority.value.compareTo(ch2.priority.value)
}