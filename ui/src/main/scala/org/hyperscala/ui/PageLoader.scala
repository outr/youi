package org.hyperscala.ui

import org.hyperscala.web.{WebpageHandler, Website, Webpage}
import org.hyperscala.realtime.Realtime
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
object PageLoader {
  def apply(title: String, url: String, creator: => Webpage) = {
    Webpage().require(BusyDialog)
    Webpage().require(Realtime)

    if (title != null) {
      BusyDialog.show(title)
    }
    try {
      // Define our page id that we're creating
      val id = Unique()
      // Contextualize and create the webpage instance and then cache it in the session
      val parentPage = Webpage()
      Website().contextualize(Website().requestContext) {
        val webpage: Webpage = creator
        WebpageHandler.cachePage(webpage)           // Make sure the page is cached for retrieval
        Webpage.updateContext(parentPage)           // Set the context back to the parent page
      }
      Realtime.sendRedirect("%s?pageId=%s".format(url, id))
    } finally {
      if (title != null) {
        BusyDialog.hide()
      }
    }
  }
}
