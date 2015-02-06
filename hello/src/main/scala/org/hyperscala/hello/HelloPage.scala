package org.hyperscala.hello

import org.hyperscala.html.attributes.InputType
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.javascript.dsl._
import org.hyperscala.realtime._
import org.hyperscala.realtime.event.browser.BrowserError
import org.hyperscala.selector.Selector
import org.hyperscala.web.Webpage
import org.hyperscala.html._
import org.powerscala.Color

import scala.util.Random

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloPage extends Webpage(HelloSite) {
  require(Realtime)
  Realtime.debugLogging := true

  json.partial(Unit) {
    case evt: BrowserError => error(s"Browser Error occurred: ${evt.message}, ${evt.obj}, ${evt.errorMessage}, ${evt.stackTrace}")
  }

  val three = new tag.Text(" 3 ")

  body.contents += new tag.H1 {
    contents += new tag.Em(content = "Testing")
    contents += " 1 "
    contents += new tag.B(content = " 2 ")
    contents += three
    contents += " 4 "
  }

  val input = new tag.Input(id = "myInput", placeHolder = "Testing")
  input.changeEvent.onRealtime {
    case evt => println(s"Input changed to ${evt.value}")
  }
  body.contents += input

  val container = new tag.Div

  body.contents += new tag.Button {
    contents += "Create"
    clickEvent.onRealtime {
      case evt => {
        println(s"Clicked!")
        container.contents += new tag.H1(content = "New Item!")
        input.placeHolder := s"Wahoo ${Random.nextInt(1000)}"
      }
    }
  }

  body.contents += new tag.Button {
    contents += "Remove"
    clickEvent.onRealtime {
      case evt => {
        println(s"Clicked!")
        container.contents.lastOption match {
          case Some(child) => child.removeFromParent()
          case None => // No children left
        }
      }
    }
  }

  body.contents += new tag.Button {
    contents += "Special 1"
    clickEvent.onRealtime {
      case evt => {
        input.value := "Green!"
      }
    }
  }

  body.contents += new tag.Button {
    contents += "Special 2"
    clickEvent.onRealtime {
      case evt => {
        HelloPage.this.eval(JavaScriptString("asdfasdf"))
      }
    }
  }

  body.contents += container
}
