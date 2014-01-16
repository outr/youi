package org.hyperscala.connect

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.jquery.jQuery
import org.hyperscala.Unique
import org.hyperscala.javascript.JavaScriptContent
import com.outr.net.http.content.StringContent

import argonaut._, Argonaut._
import org.powerscala.concurrent.Time
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.Listenable
import akka.actor.{Props, ActorSystem, Actor}
import org.powerscala.log.Logging

/**
 * Connect provides communication support to Hyperscala to allow the client to communicate to the server and the server
 * to communicate back to the client.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Connect extends Module with HttpHandler with Logging {
  val name = "connect"
  val version = Version(1)

  System.setProperty("akka.daemonic", "on")
  private val system = ActorSystem("RealtimePageActorSystem")
  private[connect] def newActor() = system.actorOf(Props[AsynchronousFunctionActor])

  object Error {
    val InvalidRequest = 1
    val PageNotFound = 2
    val ConnectionNotFound = 3
  }

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register("/js/hyperscala.connect.js", "hyperscala.connect.js")
    Website().register("/css/hyperscala.connect.css", "hyperscala.connect.css")
    Website().addHandler(this, "/hyperscala.connect/send")
    Website().addHandler(this, "/hyperscala.connect/receive")
  }

  def load() = {
    Webpage().head.contents += new tag.Link(href = "/css/hyperscala.connect.css")
    Webpage().head.contents += new tag.Script(src = "/js/hyperscala.connect.js")
    Webpage().head.contents += new tag.Script {
      contents += ConnectSession(Webpage())
    }
    Webpage().body.contents += new tag.Div(id = "hyperscala_connect_error", clazz = List("hyperscala_connect_error_hidden")) {
      contents += new tag.Div {
        contents += new tag.A(id = "hyperscala_connect_error_close", titleText = "Close", clazz = List("close"))
        contents += new tag.Div(id = "hyperscala_connect_error_message")
      }
    }
  }

  def event(f: ((Connection, Message)) => Unit) = connections().on(f)

  def on(event: String)(f: Json => Unit) = connections().on(event)(f)

  def send(event: String, data: Json, sendWhenConnected: Boolean = true) = connections().send2Client(event, data, sendWhenConnected)

  def connections(page: Webpage = Webpage()) = page.store.getOrSet("hyperscala.connect", new Connections(Webpage()))

  def onReceive(request: HttpRequest, response: HttpResponse) = {
    val content = request.contentString.get
    val jsonOption = request.url.filename match {
      case "receive" => content.decodeOption[ReceiveRequest]
      case "send" => content.decodeOption[SendRequest]
    }
    jsonOption match {
      case Some(json) => {
        Website().pages.byId[Webpage](json.pageId) match {
          case Some(page) => {
            page.checkIn()                      // Let the page know it's still in-use
            val conns = connections(page)
            conns.byId(json.connectionId) match {
              case Some(connection) => request.url.filename match {
                case "receive" => {
                  val receive = json.asInstanceOf[ReceiveRequest]
                  connection.receive(response, receive.resend)
                }
                case "send" => {
                  val send = json.asInstanceOf[SendRequest]
                  send.messages.foreach {
                    case message => connection.send2Server(message)
                  }
                  val r = SendResponse("OK")
                  response.copy(content = StringContent(r.asJson.spaces2), status = HttpResponseStatus.OK)
                }
              }
              case None => createError(response, Error.ConnectionNotFound, "Connection not found")
            }
          }
          case None => createError(response, Error.PageNotFound, "Page not found")
        }
      }
      case None => createError(response, Error.InvalidRequest, s"JSON ${request.url.filename} data was invalid. Actual content: [$content]")
    }
  }

  private def createError(response: HttpResponse, code: Int, message: String) = {
    warn(s"Connect.createError: $code - $message")
    response.copy(status = HttpResponseStatus.BadRequest(s"$code:$message"))
  }
}

class Connections(val webpage: Webpage) extends Listenable with Logging {
  private var map = Map.empty[String, Connection]

  private var backlog = List.empty[(String, Json)]

  val created = new UnitProcessor[Connection]("created")

  def isEmpty = map.isEmpty

  lazy val actor = Connect.newActor()
  val messageEvent = new UnitProcessor[(Connection, Message)]("messageEvent")

  def on(f: ((Connection, Message)) => Unit) = messageEvent.on(f)

  def on(event: String)(f: Json => Unit) = {
    messageEvent.on {
      case (connection, message) => if (message.event == event) {
        f(message.data)
      }
    }
  }

  def create() = synchronized {
    val connection = new Connection(this)
    map += connection.id -> connection
    if (backlog.nonEmpty) {     // Send backlog
      backlog.reverse.foreach {
        case (event, data) => connection.send2Client(event, data)
      }
      backlog = Nil
    }
    created.fire(connection)
    connection
  }

  def send2Client(event: String, data: Json, sendWhenConnected: Boolean) = synchronized {
    if (isEmpty && sendWhenConnected) {
      backlog = event -> data :: backlog
    } else {
      map.values.foreach(c => c.send2Client(event, data))
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

class Connection(connections: Connections) extends Logging {
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

  def send2Client(event: String, data: Json) = synchronized {
    val m = Message(nextServer2ClientId(), event, data)
    queue = m :: queue
  }

  def send2Server(message: Message) = synchronized {
    val expectedId = client2ServerId + 1
    if (message.id == expectedId) {
      client2ServerId = expectedId
      val context = Website().requestContext          // Get the context for the current thread
      val f = () => {
          Website().contextualize(context) {
            Webpage.updateContext(webpage)
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
    val json = r.asJson.spaces2
    response.copy(content = StringContent(json), status = HttpResponseStatus.OK)
  }
}

trait Request {
  def pageId: String
  def connectionId: String
  def timestamp: Long
}

case class ReceiveRequest(pageId: String, connectionId: String, timestamp: Long, resend: Boolean) extends Request

object ReceiveRequest {
  implicit def ReceiveRequestCodecJson: CodecJson[ReceiveRequest] = casecodec4(ReceiveRequest.apply, ReceiveRequest.unapply)("pageId", "connectionId", "timestamp", "resend")
}

case class SendRequest(pageId: String, connectionId: String, timestamp: Long, messages: List[Message]) extends Request

object SendRequest {
  implicit def SendRequestCodecJson: CodecJson[SendRequest] = casecodec4(SendRequest.apply, SendRequest.unapply)("pageId", "connectionId", "timestamp", "messages")
}

case class SendResponse(status: String)

object SendResponse {
  implicit def SendResponseCodecJson: CodecJson[SendResponse] = casecodec1(SendResponse.apply, SendResponse.unapply)("status")
}

case class ReceiveResponse(status: String, messages: List[Message])

object ReceiveResponse {
  implicit def ReceiveResonseCodecJson: CodecJson[ReceiveResponse] = casecodec2(ReceiveResponse.apply, ReceiveResponse.unapply)("status", "messages")
}

case class Message(id: Int, event: String, data: Json)

object Message {
  implicit def MessageCodecJson: CodecJson[Message] = casecodec3(Message.apply, Message.unapply)("id", "event", "data")
}

case class ConnectSession(page: Webpage) extends JavaScriptContent {
  def content = {   // Generate a new connection each rendering of the content
    val connection = Website().request.store.getOrSet("hyperscala.connect.session", Connect.connections(page).create())
    s"""
      |$$(document).ready(function() {
      | HyperscalaConnect.init('${page.pageId}', '${connection.id}');
      |});
    """.stripMargin
  }
}