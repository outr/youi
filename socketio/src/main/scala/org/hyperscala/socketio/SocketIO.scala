package org.hyperscala.socketio

import org.hyperscala.module.Module
import org.powerscala.{Priority, Version}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.powerscala.property.Property
import org.powerscala.concurrent.Executor
import com.outr.net.http.content.StringContent

/**
 * SocketIO module includes the JavaScript dependency and configures the website to handle connections.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object SocketIO extends Module with HttpHandler {
  private val PathRegex = "/[a-zA-Z0-9-_.]+/([a-z0-9]+)/1/.*".r

  val name = "socket.io"
  val version = Version(0, 9, 16)
  val defaults = SocketIOConfiguration()

  val resource = Property[String](default = Some(defaults.resource))
  val heartbeatTimeout = Property[Int](default = Some(60))
  val closeTimeout = Property[Int](default = Some(60))

  private var _connections = Map.empty[String, SocketIOConnection]
  Executor.scheduleWithFixedDelay(5.0, 5.0) {
    update()
  }

  protected[socketio] def add(connection: SocketIOConnection) = synchronized {
    _connections += connection.id -> connection
  }
  protected[socketio] def remove(connection: SocketIOConnection) = synchronized {
    _connections -= connection.id
  }
  def byId(id: String) = _connections.get(id)

  def init() = {
    Website().register("/js/socket.io.js", "socket.io.js")
    Website().handlers.add(this, Priority.Normal)
  }

  def load() = {
    Webpage().head.contents += new tag.Script(src = "/js/socket.io.js")
  }

  protected def update() = {
    _connections.values.foreach(_.update())
  }

  def onReceive(request: HttpRequest, response: HttpResponse) = if (request.url.path.startsWith(s"/${resource()}") && response.status == HttpResponseStatus.NotFound) {
    request.url.path match {
      case PathRegex(id) => byId(id) match {
        case Some(connection) => connection.handle(request, response)
        case None => response.copy(content = StringContent(s"Unable to find connection by id: $id"), status = HttpResponseStatus.Unauthorized)
      }
    }
  } else {
    response
  }
}