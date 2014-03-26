package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.powerscala.Color
import com.outr.net.http.session.{MapSession, Session}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.ui.PageLoader

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FramedExample extends Example {
  val iframe = new tag.IFrame(width = "500", height = "500") {
    style.backgroundColor := Color.Red
  }
  contents += iframe

  connected[Webpage[MapSession]] {
    case webpage => {
      val newPage = PageLoader.create(webpage) {
        new FramedPage(webpage.website, this)
      }
      val url = PageLoader.url(newPage, "/example/framed.html")
      iframe.src := url
    }
  }
}

class FramedPage[S <: Session](website: Website[S], parentPage: FramedExample) extends Webpage[S](website) {
  body.style.backgroundColor := Color.Yellow
  body.contents += new tag.H1(content = "IFramed Content!")
}