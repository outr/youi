package org.hyperscala.examples.ui

import com.outr.net.http.session.Session
import org.hyperscala.css.attributes.LineStyle
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.realtime._
import org.hyperscala.examples.Example
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeFrameExample extends Example {
  this.require(Realtime)

  private var count = 0

  contents += new tag.P {
    contents += "RealtimeFrame extends an IFrame to allow customized pages to be instantiated and then assigned from within the parent page."
  }

  val frame = new RealtimeFrame("/example/realtime_frame.html") {
    style.borderColor := Color.Red
    style.borderStyle := LineStyle.Solid
    style.borderWidth := 2.px
    style.borderRadius := 10.px
    style.width := 400.px
    style.height := 200.px

    connected[Webpage[Session]] {
      case webpage => currentPage := new FramedPage(webpage.website, "Preloaded Page")
    }
  }

  contents += frame
  contents += new tag.Button(content = "Load") {
    clickEvent.onRealtime {
      case evt => {
        count += 1
        val page = new FramedPage(this.webpage[Session].website, s"Page $count")
        frame.currentPage := page
      }
    }
  }
}

class FramedPage[S <: Session](website: Website[S], message: String) extends Webpage(website) {
  Realtime.sendJavaScript(this, "console.log('Wahoo!');")
  body.contents += new tag.Strong(content = s"Framed page! Message: $message")

  body.contents += new tag.Button(content = "Test") {
    clickEvent.onRealtime {
      case evt => {
        body.contents += new tag.P(content = "Added dynamically!")
      }
    }
  }
}