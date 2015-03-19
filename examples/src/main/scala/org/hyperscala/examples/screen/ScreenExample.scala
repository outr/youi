package org.hyperscala.examples.screen

import org.hyperscala.examples.Example
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.screen._
import org.hyperscala.web._
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScreenExample extends Example {
  require(Gritter)

  private val baseURI = "/example/advanced"
  val screen1URI = s"$baseURI/screen.html"
  val screen2URI = s"$baseURI/screen2.html"
  val screen3URI = s"$baseURI/screen3.html"

  val screens = new ExampleScreens(this)

  val heading = new tag.H1(content = "Example")
  contents += heading

  contents += new tag.Button(content = "Screen 1") {
    clickEvent.onRealtime {
      case evt => screens.activate(screen1URI, replace = false)
    }
  }
  contents += new tag.Button(content = "Screen 2") {
    clickEvent.onRealtime {
      case evt => screens.screen2.handle(screen2URI)
    }
  }
  contents += new tag.Button(content = "Screen 3") {
    clickEvent.onRealtime {
      case evt => screens.activate(screen3URI, replace = false)
    }
  }
  contents += new tag.Button(content = "Reload Screen 1") {
    clickEvent.onRealtime {
      case evt => screens.screen1.reLoad()
    }
  }
  contents += new tag.Button(content = "Dispose Screen 3") {
    clickEvent.onRealtime {
      case evt => screens.screen3.dispose()
    }
  }

  def notify(message: String) = Gritter.add(this.webpage, "Screen Change", message)
}

class ExampleScreens(example: ScreenExample) extends Screens(example) {
  val screen1 = screen(example.screen1URI, new HeadingScreen(example, "Screen 1"))
  val screen2 = screen(example.screen2URI, new HeadingScreen(example, "Screen 2"))
  val screen3 = screen(example.screen3URI, new HeadingScreen(example, "Screen 3"))
}

class HeadingScreen(example: ScreenExample, text: String) extends Screen {
  val textMessage = f"$text - Created: ${System.currentTimeMillis()}%tT"

  example.notify(s"Creating $text")

  override def activate() = {
    example.notify(s"Activating $text")
    example.heading.contents.replaceWith(textMessage)
  }

  override def deactivate() = {
    example.notify(s"Deactivating $text")
  }

  override def dispose() = {
    example.notify(s"Disposing $text")
  }
}