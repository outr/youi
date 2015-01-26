package org.hyperscala.connect

import akka.actor.{Actor, ActorSystem, Props}
import com.outr.net.http.{HttpApplication, HttpHandler}
import com.outr.net.http.content.StringContent
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponse, HttpResponseStatus}
import com.outr.net.http.session.Session
import org.hyperscala.event.ClientEvent
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.json4s.JsonAST.JValue
import org.powerscala.concurrent.Time
import org.powerscala.event.Listenable
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.json.JSON
import org.powerscala.log.Logging
import org.powerscala.{Unique, Version}

/**
 * Connect provides communication support to Hyperscala to allow the client to communicate to the server and the server
 * to communicate back to the client.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Connect extends Module with Logging {
  val name = "connect"
  val version = Version(1)

  System.setProperty("akka.daemonic", "on")
  private val system = ActorSystem("RealtimePageActorSystem")
  private[connect] def newActor() = system.actorOf(Props[AsynchronousFunctionActor])

  object Error {
    val InvalidRequest = 1
    val PageNotFound = 2
    val ConnectionNotFound = 3
    val NoContent = 4
  }

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/hyperscala.connect.js", "hyperscala.connect.js")
    website.register("/css/hyperscala.connect.css", "hyperscala.connect.css")
    val handler = new ConnectHandler(website)(website.manifest)
    website.addHandler(handler, "/hyperscala.connect/send")
    website.addHandler(handler, "/hyperscala.connect/receive")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Link(href = "/css/hyperscala.connect.css")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala.connect.js")
    webpage.head.contents += new tag.Script {
      contents += ConnectSession(webpage)
    }
    if (!webpage.store.getOrElse("hyperscala.connect.disableErrorDisplay", false)) {
      webpage.body.contents += new tag.Div(id = "hyperscala_connect_error", clazz = List("hyperscala_connect_error_hidden")) {
        contents += new tag.Div {
          contents += new tag.A(id = "hyperscala_connect_error_close", titleText = "Close", clazz = List("close"))
          contents += new tag.Div(id = "hyperscala_connect_error_message")
        }
      }
    }
  }

  def disableErrorDisplay[S <: Session](webpage: Webpage[S]) = webpage.store.update("hyperscala.connect.disableErrorDisplay", true)

  def event[S <: Session](webpage: Webpage[S])(f: ((Connection[S], Message)) => Unit)(implicit manifest: Manifest[S]) = {
    connections(webpage).on(f)
  }

  def on[S <: Session](webpage: Webpage[S], event: String)(f: ClientEvent => Unit)(implicit manifest: Manifest[S]) = {
    connections(webpage).on(event)(f)
  }

  def send[S <: Session](webpage: Webpage[S], event: String, data: ClientEvent, sendWhenConnected: Boolean = true)(implicit manifest: Manifest[S]) = {
    connections(webpage).send2Client(event, data, sendWhenConnected)
  }

  def connections[S <: Session](page: Webpage[S])(implicit manifest: Manifest[S]) = {
    page.store.getOrSet("hyperscala.connect", new Connections(page))
  }

  def createError(response: HttpResponse, code: Int, message: String) = {
    warn(s"Connect.createError: $code - $message")
    response.copy(status = HttpResponseStatus.BadRequest(s"$code:$message"))
  }
}

class ConnectHandler[S <: Session](website: Website[S])(implicit manifest: Manifest[S]) extends HttpHandler {
  def onReceive(request: HttpRequest, response: HttpResponse) = request.contentString match {
    case Some(content) => {
      val json = JSON.parseJSON(content)
      val o = request.url.filename match {
        case "receive" => JSON.readAndGet[ReceiveRequest](json)
        case "send" => JSON.readAndGet[SendRequest](json)
      }
      website.pages.byId[Webpage[S]](o.pageId) match {
        case Some(page) => {
          page.checkIn() // Let the page know it's still in-use
          val conns = Connect.connections(page)
          conns.byId(o.connectionId) match {
            case Some(connection) => request.url.filename match {
              case "receive" => {
                val receive = o.asInstanceOf[ReceiveRequest]
                connection.receive(response, receive.resend)
              }
              case "send" => {
                val send = o.asInstanceOf[SendRequest]
                send.messages.foreach {
                  case message => connection.send2Server(message)
                }
                val r = SendResponse("OK")
                response.copy(content = StringContent(JSON.renderJSON(JSON.parseAndGet(r))), status = HttpResponseStatus.OK)
              }
            }
            case None => Connect.createError(response, Connect.Error.ConnectionNotFound, "Connection not found")
          }
        }
        case None => Connect.createError(response, Connect.Error.PageNotFound, s"Page not found (id: ${o.pageId})")
      }
    }
    case None => Connect.createError(response, Connect.Error.NoContent, "No content was sent in the request.")
  }
}

class Connections[S <: Session](val webpage: Webpage[S])(implicit manifest: Manifest[S]) extends Listenable with Logging {
  private var map = Map.empty[String, Connection[S]]

  private var _backlog = List.empty[(String, ClientEvent)]
  def backlog = _backlog

  val created = new UnitProcessor[Connection[S]]("created")

  webpage.html.onAfterRender {
    if (_backlog.nonEmpty) {     // Send backlog after render
      _backlog.reverse.foreach {
        case (event, data) => map.values.foreach(c => c.send2Client(event, data))
      }
      _backlog = Nil
    }
  }

  def isEmpty = map.isEmpty

  lazy val actor = Connect.newActor()
  val messageEvent = new UnitProcessor[(Connection[S], Message)]("messageEvent")

  def on(f: ((Connection[S], Message)) => Unit) = messageEvent.on(f)

  def on(event: String)(f: ClientEvent => Unit) = {
    messageEvent.on {
      case (connection, message) => if (message.event == event) {
        f(message.data)
      }
    }
  }

  def create() = synchronized {
    val connection = new Connection(this)
    map += connection.id -> connection
    created.fire(connection)
    connection
  }

  def send2Client(event: String, data: ClientEvent, sendWhenConnected: Boolean) = synchronized {
    if (webpage.rendered) {
      map.values.foreach(c => c.send2Client(event, data))
    } else if (sendWhenConnected) {
      _backlog = event -> data :: _backlog
    }
  }

  def byId(id: String) = map.get(id)

  def update() = map.values.foreach(c => c.update())
}

class AsynchronousFunctionActor extends Actor {
  def receive = {
    case f: Function0[_] => f()
  }
}

class Connection[S <: Session](connections: Connections[S]) extends Logging {
  val id = Unique()

  private var server2ClientId = 0
  private var client2ServerId = 0

  def nextServer2ClientId() = synchronized {
    server2ClientId += 1
    server2ClientId
  }

  def webpage = connections.webpage

  private var queue = List.empty[Message]
  private var sentQueue = List.empty[Message]

  def update() = {}

  def send2Client(event: String, data: ClientEvent) = synchronized {
    val m = Message(nextServer2ClientId(), event, data)
    queue = m :: queue
  }

  def send2Server(message: Message) = synchronized {
    val expectedId = client2ServerId + 1
    if (message.id == expectedId) {
      client2ServerId = expectedId
      val website = webpage.website
      val request = website.request          // Get the request for the current thread
      val f = () => {
          HttpApplication.around(request) {
            connections.messageEvent.fire(this -> message)
          }
        }
      connections.actor ! f     // Process receives one at a time via actor
    } else if (message.id < expectedId) {           // We've already seen this one, ignore it
      warn(s"ignoring already received message id: ${message.id} (next expected: $expectedId)")
    } else {
      error(s"Lost messages. Expected: $expectedId but received ${message.id}.")
    }
  }

  def receive(response: HttpResponse, resend: Boolean) = {
    if (!resend) {
      Time.waitFor(30.0, precision = 0.05) {      // Wait up to 30 seconds for an entry in the queue
        webpage.checkIn()       // Keep the page alive
        queue.nonEmpty
      }

      synchronized {
        sentQueue = queue.reverse
        queue = List.empty[Message]
      }
    }
    val q = sentQueue
    val r = ReceiveResponse("OK", q)
    val json = JSON.renderJSON(JSON.parseAndGet(r))
    response.copy(content = StringContent(json), status = HttpResponseStatus.OK)
  }
}

trait Request {
  def pageId: String
  def connectionId: String
  def timestamp: Long
}

case class ReceiveRequest(pageId: String, connectionId: String, timestamp: Long, resend: Boolean) extends Request

case class SendRequest(pageId: String, connectionId: String, timestamp: Long, messages: List[Message]) extends Request

case class SendResponse(status: String)

case class ReceiveResponse(status: String, messages: List[Message])

case class Message(id: Int, event: String, data: ClientEvent)

case class ConnectSession[S <: Session](page: Webpage[S]) extends JavaScriptContent {
  def content = {   // Generate a new connection each rendering of the content
    val connection = page.website.request.store.getOrSet("hyperscala.connect.session", Connect.connections(page)(page.website.manifest).create())
    s"""
      |$$(document).ready(function() {
      | HyperscalaConnect.init('${page.pageId}', '${connection.id}');
      |});
    """.stripMargin
  }
}