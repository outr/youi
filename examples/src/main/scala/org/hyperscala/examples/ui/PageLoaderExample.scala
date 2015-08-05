package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.ui.{BusyDialog, PageLoader}
import org.hyperscala.web.Webpage

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PageLoaderExample extends Webpage with Example {
  require(BusyDialog)
  require(Realtime)

  body.contents += new tag.Button(content = "Load Page") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => reloadPage()
    }
  }

  def reloadPage() = {
    PageLoader(this, "Test Page Loader...", "/example/advanced/page_loader.html", {
      info("Loading next page...")
      Thread.sleep(2000)
      val next = new PageLoaderExample
      next.body.contents += new tag.Br
      next.body.contents += new tag.Br
      next.body.contents += new tag.Div(content = "Loaded explicitly by PageLoader!!!")
      info("Finished loading next page!")
      next
    })
  }
}
