package org.hyperscala.examples.ui


import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.clipboard.Clipboard
import org.hyperscala.web._

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ClipboardExample extends Webpage with Example {
  require(Clipboard)

  body.contents += new tag.P {
    contents += "Clipboard module provides storage and retrieval of data from the clipboard support."
  }

  val div = new tag.Div
  val input = new tag.Input(id = "myInput")
  val textArea = new tag.TextArea(id = "myTextArea", content = "My Text Area!")

  Clipboard.connect(body)
  Clipboard(this).configureDefaultHandling()
  Clipboard(this).entryAdded.on {
    case evt => println(s"Entry added to clipboard: $evt")
  }

  div.contents += input
  div.contents += textArea
  body.contents += div
}