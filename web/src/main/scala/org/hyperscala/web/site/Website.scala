package org.hyperscala.web.site

import com.outr.webcommunicator.netty._
import com.outr.webcommunicator.netty.communicator.NettyCommunicatorManager
import java.util.UUID
import org.powerscala.{Updatable, Logging}
import org.hyperscala.web.session.{MapSession, Session}
import org.powerscala.concurrent.Time._

import org.powerscala.reflect._
import org.powerscala.concurrent.Executor

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Website[S <: Session] extends NettyCommunicatorManager[WebpageConnection] with Logging with Updatable {
  implicit val thisWebsite = this

  def sessionCookieKey = getClass.getSimpleName
  def sessionCookieLifetime = Int.MaxValue

  def host: String = "localhost"
  def port: Int = 8080

  /**
   * The frequency the site, sessions, and pages will be background updated in seconds.
   *
   * Defaults to 5.0 (5 seconds)
   */
  def updateFrequency = 5.seconds

  protected def createSession(): S

  def webpage = WebContext().webpage
  def headers = WebContext().headers
  def url = WebContext().url
  def cookies = WebContext().cookies
  def session = WebContext().session.asInstanceOf[S]

  object application extends MapSession(this) {
    override def timeout = Double.MaxValue
  }

  private var lastUpdate = System.nanoTime()
  val updated = Executor.scheduleWithFixedDelay(updateFrequency, updateFrequency) {
    val currentUpdate = System.nanoTime()
    val delta = fromNanos(currentUpdate - lastUpdate)
    update(delta)
    lastUpdate = currentUpdate
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

  override def update(delta: Double) {
    super.update(delta)

    application.update(delta)
    sessions.values.foreach {
      case session => try {
        session.update(delta)
      } catch {
        case t: Throwable => errorThrown(null, t)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    bind(host, port)
    info("%s bound to %s:%s".format(getClass.getSimpleName, host, port))
  }
}

object Website {
  def apply() = WebContext().website
}
