package org.hyperscala.realtime

import com.outr.net.communicate.ConnectionHolder
import com.outr.net.http.session.Session
import org.hyperscala.IdentifiableTag
import org.hyperscala.html.HTMLTag
import org.hyperscala.realtime.event.browser.{BrowserError, InitBrowserConnection}
import org.hyperscala.realtime.event.server.ReloadPage
import org.hyperscala.web.{Website, Webpage}
import org.json4s.JsonAST.JString
import org.powerscala.json.{MapSupport, TypedSupport}
import org.powerscala.log.Logging

/**
 * RealtimeJSON sets up support for all the JSON types in Realtime.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object RealtimeJSON extends Logging {
  def init() = {
    TypedSupport.register("init", classOf[InitBrowserConnection])
    TypedSupport.register("reload", classOf[ReloadPage])
    TypedSupport.register("browserError", classOf[BrowserError])

    // Configure JSON mapping for BrowserEvents
    MapSupport.j2o.on {
      case m if m.contains("id") => ConnectionHolder.connection.holder() match {
        case webpage: Webpage => {
          val parent = m.get("parent").map(v => webpage.byId[HTMLTag](v.asInstanceOf[String])).flatten.getOrElse(webpage.html)
          val target = m.get("target").map(v => parent.byId[HTMLTag](v.asInstanceOf[String])).flatten
          val tag = m.get("id").map(v => parent.byId[HTMLTag](v.asInstanceOf[String])).flatten
          m ++ Map("parent" -> parent, "target" -> target, "tag" -> tag.orNull)
        }
        case holder => {
          warn(s"Unable to handle $m, message not connected to a webpage (Holder: $holder).")
          m
        }
      }
      case m => m
    }
    org.powerscala.json.o2j.partial(None) {
      case t: IdentifiableTag => Option(JString(t.identity))
    }
    MapSupport.o2j.on {
      case m if m.contains("tag") => m + ("id" -> m("tag")) - "tag"
      case m => m
    }

    ConnectionHolder.jsonEvent.partial(Unit) {
      case init: InitBrowserConnection => connect(init)
    }
  }

  /**
   * Routes the connection to the webpage.
   */
  private[realtime] def connect(init: InitBrowserConnection) = {
    val connection = ConnectionHolder.connection
    val site = connection.application.asInstanceOf[Website]
    site.pages.byPageId[Webpage](init.pageId) match {
      case Some(page) => {
        page.hold(connection)                           // Webpage should hold the connection
        RealtimePage(page).init()                       // Initialize the RealtimePage
        debug(s"Connected to $page.")
      }
      case None => {
        warn(s"Unable to find Webpage for id ${init.pageId} in RealtimeJSON.connect on site ${site.getClass.getSimpleName} for ${init.url}.")
        ConnectionHolder.connection.sendJSON(ReloadPage(forcedReload = true))
      }
    }
  }
}