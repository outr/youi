package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.live.{LiveEvent, LivePage}
import org.powerscala.event.ActionEvent

import org.powerscala.property._
import org.powerscala.Color
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePageExample extends LivePage {
  title := "Live Page Example"

  var count = 0
  var reversed = false

  val input = new Input {
    event.change := LiveEvent

    value.onChange {
      println("Input value changed: %s".format(value()))
    }

    listeners.synchronous {
      case evt: ActionEvent => println("Input event: %s".format(evt))
    }
  }
  contents += input

  val select = new Select {
    event.change := LiveEvent

    contents += new Option(value = "uno", content = "One")
    contents += new Option(value = "dos", content = "Two")
    contents += new Option(value = "tres", content = "Three")

    listeners.synchronous.filter.descendant() {
      case evt: PropertyChangeEvent => println(evt)
    }
  }
  contents += select

  val textArea = new TextArea {
    event.change := LiveEvent

    listeners.synchronous {
      case evt => println(outputString + " - " + evt)
    }
  }
  contents += textArea

  contents += new Button(content = "Test Button") {
    event.click := LiveEvent

    listeners.synchronous {
      case evt: ActionEvent => {
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
