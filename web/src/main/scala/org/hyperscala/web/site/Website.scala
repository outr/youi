package org.hyperscala.web.site

import com.outr.webcommunicator.netty._
import com.outr.webcommunicator.netty.communicator.NettyCommunicatorManager
import java.util.UUID
import org.powerscala.Logging
import org.hyperscala.web.session.{MapSession, Session}

import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Website[S <: Session] extends NettyCommunicatorManager[WebpageConnection] with Logging {
  implicit val thisWebsite = this

  def sessionCookieKey = getClass.getSimpleName
  def sessionCookieLifetime = Int.MaxValue

  def host: String = "localhost"
  def port: Int = 8080

  protected def createSession(): S

  def webpage = WebContext().webpage
  def headers = WebContext().headers
  def url = WebContext().url
  def cookies = WebContext().cookies
  def session = WebContext().session.asInstanceOf[S]
  val application = new MapSession {
    override def timeout = Double.MaxValue
  }

  override def initialize() {
    super.initialize()

    // Statically load all referenced vals that are of type WebResourceHandler
    getClass.methods.foreach {
      case m if (m.returnType.`type`.hasType(classOf[WebResource])) => {
        val resource = m[WebResource](Website.this)
        register(resource)
      }
      case _ =>
    }
  }

  protected[web] final def instantiateSession(id: String) = {
    val s = createSession()
    synchronized {
      _sessions += id -> s
    }
    s
  }

  protected[web] final def destroySession(session: Session) = {
    session.dispose()
    synchronized {
      _sessions.find(t => t._2 == session) match {
        case Some((key, value)) => _sessions -= key
        case None => warn("Unable to find session key to remove session!")
      }
    }
  }

  private var _sessions = Map.empty[String, S]
  def sessions = _sessions

  protected def instantiate(id: UUID) = new WebpageConnection(id)

  def errorThrown(page: Webpage, t: Throwable) = error("Error occurred on page: %s".format(page), t)

  def main(args: Array[String]): Unit = {
    bind(host, port)
    info("%s bound to %s:%s".format(getClass.getSimpleName, host, port))
  }
}

object Website {
  def apply() = WebContext().website
}
