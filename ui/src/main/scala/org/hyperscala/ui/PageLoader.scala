package org.hyperscala.ui

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.Unique
import org.hyperscala.realtime.Realtime

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
      WebContext.wrap {
        WebContext.session := WebContext.session()      // Load session from the higher context and apply it to the new one
        val webpage: Webpage = creator
        Website().session(id) = webpage
      }
      Realtime.sendRedirect("%s?pageId=%s".format(url, id))
    } finally {
      if (title != null) {
        BusyDialog.hide()
      }
    }
  }
}
