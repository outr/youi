package org.hyperscala.web

import handler.{DefaultResourceHandler, ContentHandler}
import org.powerscala.property.{Property, PropertyParent}
import org.powerscala.hierarchy.{ContainerView, MutableContainer}
import javax.servlet.ServletConfig
import javax.servlet.http.{Cookie, HttpServletResponse, HttpServletRequest}
import session.{MapSession, Session}
import org.hyperscala.html.attributes.Method
import org.hyperscala.Unique
import org.powerscala.concurrent.Executor
import org.powerscala.concurrent.Time._
import org.powerscala.Updatable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Website[S <: Session] extends MutableContainer[ContentHandler] with PropertyParent with Updatable {
  implicit val thisWebsite = this

  /**
   * Defines how long the sessionId cookie will live.
   *
   * Defaults to Int.MaxValue.
   */
  def sessionIdLifetime = Int.MaxValue

  /**
   * The frequency the site, sessions, and pages will be background updated in seconds.
   *
   * Defaults to 15.0 (15 seconds)
   */
  def updateFrequency = 15.seconds

  private var lastUpdate = System.nanoTime()
  val updates = Executor.scheduleWithFixedDelay(updateFrequency, updateFrequency) {
    val currentUpdate = System.nanoTime()
    val delta = fromNanos(currentUpdate - lastUpdate)
    update(delta)
    lastUpdate = currentUpdate
  }

  val application = new MapSession {
    override def timeout = Double.MaxValue
  }
  private var _sessions = Map.empty[String, S]
  private val _session = new ThreadLocal[S]
  private val _servletRequest = new ThreadLocal[HttpServletRequest]
  private val _servletResponse = new ThreadLocal[HttpServletResponse]

  val handlers = new ContainerView[ContentHandler](this, null, Website.prioritySort)
  def sessions = _sessions.values

  val name = Property[String]("name", null)

  contents += DefaultResourceHandler

  def reload(config: ServletConfig) = {
    name := config.getServletContext.getServletContextName      // Load the web application name
  }

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val uri = request.getRequestURI
    Website.instance.set(this)
    _servletRequest.set(request)
    _servletResponse.set(response)
    _session.set(loadSession(request))
    try {
      lookupHandler(uri) match {
        case Some(handler) => handler(method, request, response)
        case None => response.sendError(HttpServletResponse.SC_NOT_FOUND, "The page could not be found: %s".format(uri))
      }
    } finally {
      Website.instance.set(null)
      _servletRequest.set(null)
      _servletResponse.set(null)
      _session.set(null.asInstanceOf[S])
    }
  }

  def session: S = _session.get()

  /**
   * Destroys the existing session and creates a new one.
   */
  def disposeSession() = {
    destroySession(servletRequest)
    _session.set(loadSession(servletRequest))
  }

  def servletRequest = _servletRequest.get()
  def servletResponse = _servletResponse.get()

  protected def lookupHandler(uri: String) = handlers.find(ch => ch.matches(uri))

  protected def loadSession(request: HttpServletRequest) = {
    val sessionKey = classOf[Session].getName
    val sessionId = cookies.getOrSet(sessionKey, Unique(), maxAge = sessionIdLifetime).getValue
    val session = _sessions.getOrElse(sessionId, createSession)
    _sessions += sessionId -> session
    session
  }

  protected def destroySession(request: HttpServletRequest) = {
    val sessionKey = classOf[Session].getName
    cookies(sessionKey) match {
      case Some(cookie) => _sessions.get(cookie.getValue) match {
        case Some(session) => {
          session.dispose()
          _sessions -= cookie.getValue
        }
        case None => // Couldn't find the session to destroy
      }   // Remove the session from the list
      case None => // No session to destroy
    }
  }

  protected def createSession: S

  /**
   * Handles any errors that occur on pages within this website.
   */
  def errorOccurred(t: Throwable) = {
    t.printStackTrace()
  }

  /**
   * Called when the web context is stopping.
   */
  def destroy() = {
    updates.cancel(false)
  }

  val cookies = new Seq[Cookie] {
    private def array = servletRequest.getCookies match {
      case null => Array[Cookie]()
      case cs => cs
    }

    def length = array.length

    def apply(idx: Int) = array(idx)

    def iterator = array.iterator

    def add(name: String,
            value: String,
            comment: String = null,
            domain: String = null,
            maxAge: Int = -1,
            path: String = "/",
            secure: Boolean = false,
            version: Int = 0) = {
      val cookie = new Cookie(name, value)
      if (comment != null) cookie.setComment(comment)
      if (domain != null) cookie.setDomain(domain)
      cookie.setMaxAge(maxAge)
      if (path != null) cookie.setPath(path)
      cookie.setSecure(secure)
      cookie.setVersion(version)
      servletResponse.addCookie(cookie)
      cookie
    }

    def getOrSet(name: String,
                 value: => String,
                 comment: String = null,
                 domain: String = null,
                 maxAge: Int = -1,
                 path: String = "/",
                 secure: Boolean = false,
                 version: Int = 0) = {
      apply(name) match {
        case Some(cookie) => cookie
        case None => add(name, value, comment, domain, maxAge, path, secure, version)
      }
    }

    def delete(name: String) = add(name, "", maxAge = 0)

    def apply(name: String) = this.find(c => c.getName == name)
  }

  val cached = new {
    def storage(scope: Scope) = scope match {
      case Scope.Application => application
      case Scope.Session => session
      case Scope.Request => null
    }

    /**
     * Caches the content supplied in the specified Scope based on the key provided. The content will be reused until
     * the "clear" method is invoked with the key.
     *
     * If cache is set to false, if the default content is used it will not be cached. Defaults to true.
     */
    def apply[T](key: String, scope: Scope = Scope.Session, cache: Boolean = true)(f: => T) = {
      val storage = this.storage(scope)
      val stored = if (storage != null) {
        storage.get[T](key)
      } else {
        None
      }
      val content = stored match {
        case Some(value) => value
        case None => f
      }
      if (storage != null && cache) {
        storage(key) = content
      }
      content
    }

    /**
     * Gets and removes the value from the scope.
     */
    def poll[T](key: String, scope: Scope = Scope.Session) = {
      val response = get[T](key, scope)
      clear(key, scope)
      response
    }

    def get[T](key: String, scope: Scope = Scope.Session) = {
      val storage = this.storage(scope)
      if (storage != null) {
        storage.get[T](key)
      } else {
        None
      }
    }

    /**
     * Updates the value cached value with the supplied function and then returns it.
     */
    def set[T](key: String, scope: Scope = Scope.Session)(f: => T) = {
      val storage = this.storage(scope)
      val content = f
      if (storage != null) {
        storage(key) = content
      }
      content
    }

    /**
     * Clears a cached item from the scope to be recreated upon next load.
     */
    def clear(key: String, scope: Scope = null) = {
      val storage = this.storage(scope)
      if (storage != null) {
        storage.remove(key)
      } else { // No scope defined, so remove from all scopes
        application.remove(key)
        session.remove(key)
      }
    }
  }

  override def update(delta: Double) {
    Website.instance.set(this)
    try {
      super.update(delta)
      application.update(delta)
      sessions.foreach {
        case session => {
          _session.set(session)
          try {
            session.update(delta)
          } finally {
            _session.set(null.asInstanceOf[S])
          }
        }
      }
    } finally {
      Website.instance.set(null)
    }
  }
}

object Website {
  private val instance = new ThreadLocal[Website[_ <: Session]]

  def apply() = instance.get()

  val prioritySort = (ch1: ContentHandler, ch2: ContentHandler) => -ch1.priority.value.compareTo(ch2.priority.value)
}