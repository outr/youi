package org.hyperscala.realtime

import org.hyperscala.html._
import org.hyperscala.web.{Website, Scope, WebpageHandler, Webpage}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeFrame(pageURL: String) extends tag.IFrame(src = "about:blank") {
  var currentPage = Property[Webpage]()
  @volatile private var webpage: Webpage = _

  connected[Website] {
    case site => {
      webpage = root[Webpage].get
      updatePage()
      currentPage.change.on {
        case evt => updatePage()
      }
    }
  }

  private def updatePage() = {
    val url = currentPage.get match {
      case Some(page) => {
        WebpageHandler.cachePage(page, Scope.Page, None)        // Cache the page so it can be retrieved
        s"$pageURL?pageId=${page.pageId}"
      }
      case None => "about:blank"
    }
    src := url
  }
}