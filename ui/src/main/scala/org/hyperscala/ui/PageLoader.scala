package org.hyperscala.ui

import org.hyperscala.web.{WebpageHandler, Webpage}
import org.hyperscala.realtime.Realtime
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object PageLoader {
  def apply[S <: Session](webpage: Webpage[S], title: String, url: String, creator: => Webpage[S]) = {
    webpage.require(BusyDialog)
    webpage.require(Realtime)

    if (title != null) {
      BusyDialog.show(webpage, title)
    }
    val newPage: Webpage[S] = creator                                         // Instantiate our new page
    WebpageHandler.cachePage(newPage)                                         // Cache it in the WebpageHandler
    Realtime.sendRedirect(webpage, s"$url?pageId=${newPage.pageId}")          // Redirect to the new page by id
  }
}
