package org.hyperscala.web.site

import com.outr.webcommunicator.netty.communicator.NettyCommunicatorManager
import java.util.UUID
import org.powerscala.Updatable
import org.hyperscala.web.session.{MapSession, Session}
import org.powerscala.concurrent.Time._

import org.powerscala.concurrent.Executor
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import com.outr.webcommunicator.netty.handler.RequestHandler
import com.outr.webcommunicator.netty.{WebResource, NettyWebapp}
import org.hyperscala.context.Contextual
import org.jboss.netty.handler.codec.http.HttpRequest
import org.powerscala.reflect._
import annotation.tailrec
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Website[S <: Session] extends NettyCommunicatorManager[WebpageConnection] with Logging with Updatable {
  implicit val thisWebsite = this

  /**
   * The key used for cookie naming.
   *
   * Defaults to the class name.
   */
  def sessionCookieKey = getClass.getSimpleName

  /**
   * The lifetime of the cookie in seconds.
   *
   * Defaults to Int.MaxValue.
   */
  def sessionCookieLifetime = Int.MaxValue

  /**
   * If set to true will attempt to set a wildcard domain cookie to match sub-domains.
   *
   * Defaults to true.
   */
  def sessionCookieWildcard = true

  /**
   * The host to default bind to.
   *
   * Defaults to null (wildcard)
   */
  def host: String = null

  /**
   * The port to default bind to.
   *
   * Defaults to 8080
   */
  def port: Int = 8080

  /**
   * The frequency the site, sessions, and pages will be background updated in seconds.
   *
   * Defaults to 5.0 (5 seconds)
   */
  def updateFrequency = 5.seconds

  protected def createSession(): S

  private var _sessions = Map.empty[String, S]
  val sessions = new {
    def map = _sessions
    def values = _sessions.values
    def valuesByType[T](implicit manifest: Manifest[T]) = {
      values.flatMap {
        case session => session.values.collect {
          case v if (v.getClass.hasType(manifest.erasure)) => v.asInstanceOf[T]
        }
      }
    }
  }

  def webpage = WebContext.webpage()
  def headers = WebContext.headers()
  def url = WebContext.url()
  def cookies = WebContext.cookies()
  def session = WebContext.session().asInstanceOf[S]
  def localAddress = WebContext.localAddress()
  def remoteAddress = WebContext.remoteAddress()

  register(SessionResources)    // Allow for session-based resources

  object application extends MapSession {
    override def timeout = Double.MaxValue
  }

  /**
   * Registers this resource only for the current session. This is a convenience feature to make a resource available
   * to a specific session but not visible to anyone else.
   *
   * @param resource to register to the current session
   */
  def registerSession(resource: WebResource) = SessionResources.register(resource)

  private var lastUpdate = System.nanoTime()
  val updated = Executor.scheduleWithFixedDelay(updateFrequency, updateFrequency) {
    val currentUpdate = System.nanoTime()
    val delta = fromNanos(currentUpdate - lastUpdate)
    update(delta)
    lastUpdate = currentUpdate
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

  override protected def invokeHandler(context: ChannelHandlerContext, event: MessageEvent, handler: RequestHandler) {
    handler match {
      case contextual: Contextual => WebContext.contextualize(contextual) {
        WebContext.checkIn()
        super.invokeHandler(context, event, handler)
      }
      case _ => super.invokeHandler(context, event, handler)
    }
  }

  protected def instantiate(id: UUID) = new WebpageConnection(id)

  override protected def contextualize[T](context: ChannelHandlerContext, event: MessageEvent)(f: => T): T = {
    event.getMessage match {
      case request: HttpRequest => WebContext.wrap {
        WebContext.parse(context, request)
        f
      }
      case _ => f
    }
  }

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
    val hostname = host match {
      case null => "*"
      case _ => host
    }
    info("%s bound to %s:%s".format(getClass.getSimpleName, hostname, port))
  }
}

object Website {
  def apply() = NettyWebapp().asInstanceOf[Website[_ <: Session]]
}

object SessionResources extends WebResource {
  private val key = "sessionWebResources"

  def register(resource: WebResource) = {
    val resources = Website().session.getOrElse[List[WebResource]](key, Nil)
    val updated = (resource :: resources).sortBy(h => h.priority.value)
    Website().session(key) = updated
  }

  def request(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent): Option[RequestHandler] = {
    val resources = Website().session.getOrElse[List[WebResource]](key, Nil)
    findFirst(webapp, context, event, resources)
  }

  @tailrec
  private def findFirst(webapp: NettyWebapp,
                        context: ChannelHandlerContext,
                        event: MessageEvent,
                        resources: List[WebResource]): Option[RequestHandler] = {
    if (resources.isEmpty) {
      None
    } else {
      val head = resources.head
      val option = head.request(webapp, context, event)
      if (option.nonEmpty) {
        option
      } else {
        findFirst(webapp, context, event, resources.tail)
      }
    }
  }
}