package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.ui.{PageLoader, BusyDialog}
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import language.reflectiveCalls
import com.outr.net.http.session.Session

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
