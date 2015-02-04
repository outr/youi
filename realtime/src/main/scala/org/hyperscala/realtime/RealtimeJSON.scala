package org.hyperscala.realtime

import com.outr.net.communicate.ConnectionHolder
import org.hyperscala.html.HTMLTag
import org.hyperscala.realtime.Realtime._
import org.hyperscala.realtime.event.browser.InitBrowserConnection
import org.hyperscala.realtime.event.server.ReloadPage
import org.hyperscala.web.Webpage
import org.powerscala.json.{MapSupport, TypedSupport}

/**
 * RealtimeJSON sets up support for all the JSON types in Realtime.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object RealtimeJSON {
  def init() = {
    TypedSupport.register("init", classOf[InitBrowserConnection])
    TypedSupport.register("reload", classOf[ReloadPage])

    // Configure "id" -> "tag" for BrowserEvents
    MapSupport.j2o.on {
      case m if m.contains("id") => ConnectionHolder.connection.holder() match {
        case webpage: Webpage[_] => {
          val parent = m.get("parent").map(v => webpage.byId[HTMLTag](v.asInstanceOf[String])).flatten.getOrElse(webpage.body)
          val target = m.get("target").map(v => parent.byId[HTMLTag](v.asInstanceOf[String])).flatten
          val tag = m.get("id").map(v => parent.byId[HTMLTag](v.asInstanceOf[String])).flatten
          m ++ Map("parent" -> parent, "target" -> target, "tag" -> tag)
        }
        case holder => {
          warn(s"Unable to handle $m, message not sent for webpage: $holder.")
          m
        }
      }
      case m => m
    }

    ConnectionHolder.json.partial(Unit) {
      case init: InitBrowserConnection => Realtime.connect(init)
    }
  }
}