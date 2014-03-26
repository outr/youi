package org.hyperscala.ui

import org.hyperscala.web.Webpage
import org.hyperscala.realtime.Realtime
import org.powerscala.Unique
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
    try {
      // Define our page id that we're creating
      val id = Unique()
      throw new RuntimeException("BROKEN!")
      // Contextualize and create the webpage instance and then cache it in the session
//      webpage.website.contextualize(webpage.website.requestContext) {
//        val webpage: Webpage = creator
//        WebpageHandler.cachePage(webpage)           // Make sure the page is cached for retrieval
//        Webpage.updateContext(parentPage)           // Set the context back to the parent page
//      }
//      Realtime.sendRedirect("%s?pageId=%s".format(url, id))
    } finally {
      if (title != null) {
//        BusyDialog.hide()
      }
    }
  }
}
