package org.hyperscala.examples.screen

import com.outr.net.http.session.Session
import org.hyperscala.examples.Example
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.screen.{Screen, Screens}
import org.hyperscala.web._
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScreenExample extends Example {
  require(Gritter)

  private val baseURI = "/example/advanced"
  private val screen1URI = s"$baseURI/screen.html"
  private val screen2URI = s"$baseURI/screen2.html"
  private val screen3URI = s"$baseURI/screen3.html"

  private var screens: Screens = _

  val heading = new tag.H1(content = "Example")
  contents += heading

  connected[Webpage[Session]] {
    case webpage => {
      screens = new Screens(webpage)
      screens.addScreen(screen1URI, new HeadingScreen(ScreenExample.this, "Screen 1"))
      screens.addScreen(screen2URI, new HeadingScreen(ScreenExample.this, "Screen 2"))
      screens.addScreen(screen3URI, new HeadingScreen(ScreenExample.this, "Screen 3"))
    }
  }

  contents += new tag.Button(content = "Screen 1") {
    clickEvent.onRealtime {
      case evt => screens.activate(screen1URI, replace = false)
    }
  }
  contents += new tag.Button(content = "Screen 2") {
    clickEvent.onRealtime {
      case evt => screens.activate(screen2URI, replace = false)
    }
  }
  contents += new tag.Button(content = "Screen 3") {
    clickEvent.onRealtime {
      case evt => screens.activate(screen3URI, replace = false)
    }
  }

  def notify(message: String) = Gritter.add(this.webpage, "Screen Change", message)
}

class HeadingScreen(example: ScreenExample, text: String) extends Screen {
  override def activate() = {
    example.notify(s"Activating $text")
    example.heading.contents.replaceWith(text)
  }

  override def deactivate() = {
    example.notify(s"Deactivating $text")
  }
}