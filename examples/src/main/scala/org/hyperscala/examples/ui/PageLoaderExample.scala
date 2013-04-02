package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.{PageLoader, BusyDialog}
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PageLoaderExample extends Webpage {
  require(BusyDialog)
  require(Realtime)

  body.contents += new tag.Button(content = "Load Page") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => reloadPage()
    }
  }

  def reloadPage() = {
    PageLoader("Test Page Loader...", "/example/page_loader.html", {
      info("Loading next page...")
      Thread.sleep(5000)
      val next = new PageLoaderExample
      next.body.contents += new tag.Br
      next.body.contents += new tag.Br
      next.body.contents += new tag.Div(content = "Loaded explicitly by PageLoader!!!")
      info("Finished loading next page!")
      next
    })
  }
}
