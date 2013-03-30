package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.module.DynamicURL
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}

import org.powerscala.convert.string._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DynamicURLExample extends Example {
  Webpage().require(DynamicURL)

  val test = DynamicURL().property("test", "")
  test.onChange {
    writeValues()
  }
  val num = DynamicURL().property("num", 2)
  num.onChange {
    writeValues()
  }

  contents += new tag.Button(content = "Set Hash") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        test := "hello"
        num := 5
      }
    }
  }

  def writeValues() = {
    println("Test: %s, Num: %s".format(test(), num()))
  }
}