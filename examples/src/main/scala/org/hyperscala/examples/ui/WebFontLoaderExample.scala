package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.ui.module.WebFontLoader
import org.hyperscala.web._
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebFontLoaderExample extends Example {
  this.require(WebFontLoader)
  this.require(Gritter)

  loadFont("Pacifico")

  contents += new tag.P(content = "WebFontLoader wraps TypeKit's WebFontLoader project to allow dynamically loading of web fonts.")

  contents += new tag.P(content = "Example of preloaded Pacifico from Google Fonts.") {
    style.fontFamily := "Pacifico, Times"
    style.fontSize := 32.px
  }

  contents += new tag.P(content = "Example of not preloaded Rock Salt from Google Fonts.") {
    style.fontFamily := "Rock Salt, Times"
    style.fontSize := 32.px
  }

  contents += new tag.P(content = "Example of not preloaded Inconsolata as custom font.") {
    style.fontFamily := "Inconsolata, Times"
    style.fontSize := 32.px
  }

  contents += new tag.Button(content = "Load Rock Salt Font") {
    clickEvent.onRealtime {
      case evt => {
        loadFont("Rock Salt")
        removeFromParent()
      }
    }
  }
  contents += new tag.Button(content = "Load Inconsolata Font") {
    clickEvent.onRealtime {
      case evt => {
        connected[Webpage] {
          case webpage => WebFontLoader(webpage).custom("Inconsolata", "normal", "400", "http://fonts.gstatic.com/s/inconsolata/v9/BjAYBlHtW3CJxDcjzrnZCIbN6UDyHWBl620a-IRfuBk.woff", List("Inconsolata"))
        }
        removeFromParent()
      }
    }
  }
  contents += new tag.Button(content = "Load Bangers Font") {
    clickEvent.onRealtime {
      case evt => {
        loadFont("Bangers")
        removeFromParent()
      }
    }
  }

  def loadFont(family: String) = connected[Webpage] {
    case webpage => {
      val loader = WebFontLoader(webpage)
      loader.onLoad(family) {
        Gritter.add(webpage, "Font Loaded", s"$family successfully loaded")
      }
      loader.google(List(family))
    }
  }
}
