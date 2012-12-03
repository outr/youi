package org.hyperscala.web.site.realtime

import org.hyperscala.web.module._
import org.hyperscala.web.site.{WebpageConnection, Webpage, Website}

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import java.util.UUID
import annotation.tailrec

import org.powerscala.json._
import org.hyperscala.web.module.IdentifyTags
import org.hyperscala.web.site.JavaScriptMessage
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module {
  def name = "realtime"

  def version = Version(1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery182), IdentifyTags)

  def load(page: Webpage) = {   // TODO: fix bug causing jQuery to be loaded after other scripts - prioritization
    // Configure JavaScript on page
    Website().register("/js/communicator.js", "communicator.js")
    Website().register("/js/realtime.js", "realtime.js")
    page.head.contents += new tag.Script(src = "/js/communicator.js")
    page.head.contents += new tag.Script(src = "/js/realtime.js")
    page.head.contents += new tag.Script(content = new JavaScriptContent {
      def content = {   // Every page request we create a new connection
        val id = UUID.randomUUID()
        val connection = Website().create(id)
        connection.page = page
        Realtime.addConnection(page, connection)
        "connectRealtime('%s');".format(id.toString)
      }

      protected def content_=(content: String) {}
    })
  }

  private val connectionsKey = "webpageConnections"

  def getConnections(page: Webpage) = synchronized {
    val connections = page.store.getOrElse[List[WebpageConnection]](connectionsKey, Nil)
    val updated = connections.filterNot(c => c.disposed)
    if (updated != connections) {
      page.store(connectionsKey) = updated
    }
    connections
  }

  def addConnection(page: Webpage, connection: WebpageConnection) = synchronized {
    val connections = page.store.getOrElse[List[WebpageConnection]](connectionsKey, Nil).filterNot(c => c.disposed)
    page.store(connectionsKey) = connection :: connections
  }

  def broadcast(event: String, message: Any, page: Webpage = Webpage()) = synchronized {
    val connections = getConnections(page)
    val content = message match {
      case s: String => s
      case other => generate(other)
    }
    sendRecursive(page, event, content, connections)
  }

  def sendJavaScript(instruction: String, content: String = null) = {
    broadcast("eval", JavaScriptMessage(instruction, content))
  }

  def sendRedirect(url: String) = {
    sendJavaScript("window.location.href = content;", url)
  }

  def reload(fresh: Boolean = false) = {
    sendJavaScript("location.reload(%s);".format(fresh))
  }

  @tailrec
  private def sendRecursive(page: Webpage, event: String, message: String, connections: List[WebpageConnection]): Unit = {
    if (connections.nonEmpty) {
      val c = connections.head
      c.send(event, message)
      sendRecursive(page, event, message, connections.tail)
    }
  }
}
