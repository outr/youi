package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.live.{ChangeEvent, ClickEvent, LiveEvent, LivePage}

import org.powerscala.property._
import org.powerscala.Color
import tag._
import org.powerscala.event.ActionEvent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePageExample extends LivePage {
  title := "Live Page Example"

  var count = 0
  var reversed = false

  val input = new Input {
    event.change := LiveEvent()

    value.onChange {
      println("Input value changed: %s".format(value()))
    }

    listeners.synchronous {
      case evt: ActionEvent => println("Input event: %s".format(evt))
    }
  }
  contents += input

  val select = new Select {
    event.change := LiveEvent()

    contents += new tag.Option(value = "uno", content = "One")
    contents += new tag.Option(value = "dos", content = "Two")
    contents += new tag.Option(value = "tres", content = "Three")

    listeners.synchronous {
      case evt: ChangeEvent => println("Selected: %s".format(selected))
    }
  }
  contents += select

  val textArea = new TextArea {
    event.change := LiveEvent()

    listeners.synchronous {
      case evt: ChangeEvent => println(content())
    }
  }
  contents += textArea

  contents += new Button(content = "Test Button") {
    event.click := LiveEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        println("Selected: %s".format(select.selected))
        input.value := "Button clicked %s".format(count)
        contents.replaceWith("Test Button %s".format(count))
        style.color := Color.values.random
        if (count >= 10) {
          reversed = true
        } else if (count <= 0) {
          reversed = false
        }
        if (reversed) {
          count -= 1
          LivePageExample.this.contents -= LivePageExample.this.contents.last
        } else {
          count += 1
          LivePageExample.this.contents += new Div {
            contents += "Testing %s!".format(count)
          }
        }
      }
    }
  }
}
