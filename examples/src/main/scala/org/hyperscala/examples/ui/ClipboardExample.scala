package org.hyperscala.examples.ui


import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.ui.clipboard.Clipboard
import org.hyperscala.web._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ClipboardExample extends Example {
  this.require(Clipboard)

  contents += new tag.P {
    contents += "Clipboard module provides storage and retrieval of data from the clipboard support."
  }

  val div = new tag.Div
  val input = new tag.Input(id = "myInput")
  val textArea = new tag.TextArea(id = "myTextArea", content = "My Text Area!")

  connected[Webpage] {
    case webpage => {
      Clipboard.connect(page.body)
      Clipboard(webpage).configureDefaultHandling()
      Clipboard(webpage).entryAdded.on {
        case evt => println(s"Entry added to clipboard: $evt")
      }
    }
  }

  div.contents += input
  div.contents += textArea
  contents += div
}