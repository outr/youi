package org.hyperscala.socketio

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.Unique
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.content.StringContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SocketIOConnection(variableName: String = "socket") {
  val id = Unique()
  val page = Webpage()
  def defaults = SocketIO.defaults

  private var configuration: SocketIOConfiguration = _
  private var sessionId: String = _
  private var connected = false

  def connect(configuration: SocketIOConfiguration = defaults) = {
    connected = false
    SocketIO.add(this)
    val conf = configuration.copy(resource = s"${configuration.resource}/$id")
    this.configuration = conf
    page.head.contents += new tag.Script {
      val requestURL = Website().request.url
      val js = s"$variableName = io.connect('${requestURL.base}', ${conf.toJSON});"
      contents += JavaScriptString(js)
    }
  }

  protected[socketio] def handle(request: HttpRequest, response: HttpResponse) = {
    val sessionId = request.url.filename
    if (sessionId.isEmpty) {      // Handshake request
      this.sessionId = Unique()
      respond(s"${this.sessionId}:${SocketIO.heartbeatTimeout()}:${SocketIO.closeTimeout()}:xhr-polling", response)
    } else if (sessionId == this.sessionId) {
      if (!connected) {
        connected = true
        respond("1::", response)
      } else {
        println(s"Handle: $request")
        response
      }
    } else {                      // Invalid session id
      response.copy(status = HttpResponseStatus.ServiceUnavailable)
    }
  }

  protected def respond(message: String, response: HttpResponse) = {
    response.copy(status = HttpResponseStatus.OK, content = StringContent(message))
  }

  def update() = {
    // TODO: validate timeouts
  }
}