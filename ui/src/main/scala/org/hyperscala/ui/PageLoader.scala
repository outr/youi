package org.hyperscala.ui

import org.hyperscala.web.{Scope, WebpageHandler, Webpage}
import org.hyperscala.realtime.Realtime
import com.outr.net.http.session.Session
import org.hyperscala.javascript.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object PageLoader {
  def create[S <: Session](webpage: Webpage[S])(creator: => Webpage[S]) = {
    val newPage: Webpage[S] = creator                                         // Instantiate our new page
    WebpageHandler.cachePage(newPage, Scope.Page, None)                       // Cache it in the WebpageHandler
    newPage
  }

  def url[S <: Session](webpage: Webpage[S], path: String) = s"$path?pageId=${webpage.pageId}"

  def apply[S <: Session](webpage: Webpage[S], title: String, path: String, creator: => Webpage[S]) = {
    webpage.require(BusyDialog)
    webpage.require(Realtime)

    if (title != null) {
      BusyDialog.show(webpage, title)
    }
    val newPage = create(webpage)(creator)
    val pageURL = url(newPage, path)
    webpage.eval(window.location.href := pageURL)      // Redirect to the new page by id
  }
}