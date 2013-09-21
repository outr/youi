package org.hyperscala.examples.ui


import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.ui.clipboard.Clipboard

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ClipboardExample extends Example {
  page.require(Clipboard)

  val div = new tag.Div
  val input = new tag.Input(id = "myInput")
  val textArea = new tag.TextArea(id = "myTextArea", content = "My Text Area!")

  Clipboard.connect(page.body)
  Clipboard().configureDefaultHandling()
  Clipboard().entryAdded.on {
    case evt => println(s"Entry added to clipboard: $evt")
  }

  div.contents += input
  div.contents += textArea
  contents += div
}