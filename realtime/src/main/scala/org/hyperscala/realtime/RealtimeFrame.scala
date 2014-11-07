package org.hyperscala.realtime

import com.outr.net.http.session.Session
import org.hyperscala.connect.Connect
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery
import org.hyperscala.jquery.stylesheet.jQueryStyleSheet
import org.hyperscala.module.Module
import org.hyperscala.selector.Selector
import org.hyperscala.web.module.IdentifyTags
import org.hyperscala.web.{Webpage, WebpageHandler, Website}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeFrame(pageURL: String, singleConnection: Boolean = true) extends tag.IFrame(src = "about:blank") {
  val currentPage = Property[Webpage[_ <: Session]]()
  @volatile private var webpage: Webpage[Session] = _

  connected[Webpage[Session]] {
    case page => {
      webpage = page
      updatePage()
      currentPage.change.on {
        case evt => updatePage()
      }
    }
  }

  private def updatePage() = {
    val url = currentPage.get match {
      case Some(page) => {
        if (singleConnection) {
          page.remove(Realtime)
          page.remove(Connect)
          page.require(RealtimeFrame)
          page.store("parentPage") = webpage
          page.store("realtimeFrame") = this
        }
        val realtime = RealtimePage(page)            // Create RealtimePage to listen

        if (singleConnection) {
          // Process unsent backlog
          val backlog = realtime.connections.backlog
          backlog.foreach {
            case (event, data) => RealtimePage(webpage).send(event, data, sendWhenConnected = true)
          }
        }
        WebpageHandler.cachePage(page)
        s"$pageURL?pageId=${page.pageId}"
      }
      case None => "about:blank"
    }
    Realtime.sendJavaScript(webpage, s"$$('#$identity').get(0).src = '$url';", selector = Some(Selector.id(this)), onlyRealtime = false)
  }

  override def xmlChildren = currentPage.get match {
    case Some(page) => page.html.contents.asInstanceOf[VisibleContents]
    case None => super.xmlChildren
  }
}

object RealtimeFrame extends Module {
  override val name = Realtime.name
  override val version = Realtime.version

  override def dependencies = List(jQuery, jQueryStyleSheet, IdentifyTags)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/realtime_frame.js", "realtime_frame.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Meta(httpEquiv = "expires", content = "0")
    webpage.head.contents += new tag.Script(src = "/js/realtime_frame.js")
    webpage.head.contents += new tag.Script(src = "/js/realtime.js")
  }
}