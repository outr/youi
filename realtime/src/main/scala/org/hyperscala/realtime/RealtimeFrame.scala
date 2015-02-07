package org.hyperscala.realtime

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.web.{WebpageHandler, Webpage}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeFrame(pageURL: String) extends tag.IFrame(src = "about:blank") {
  var currentPage = Property[Webpage[_ <: Session]]()
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
        WebpageHandler.cachePage(page)        // Cache the page so it can be retrieved
        s"$pageURL?pageId=${page.pageId}"
      }
      case None => "about:blank"
    }
    src := url
  }
}