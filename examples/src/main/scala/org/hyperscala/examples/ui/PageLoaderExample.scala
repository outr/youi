package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.ui.{PageLoader, BusyDialog}
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PageLoaderExample extends Webpage {
  require(BusyDialog)
  require(Realtime)

  body.contents += new tag.Button(content = "Load Page") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => reloadPage()
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
