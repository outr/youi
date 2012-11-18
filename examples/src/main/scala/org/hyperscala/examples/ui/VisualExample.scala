package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.Visual
import org.hyperscala.css.attributes._

import org.powerscala.property._
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.site.realtime.RealtimeWebpage
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class VisualExample extends RealtimeWebpage {
  body.style.font.family := "sans-serif"

  val property = Property[Test]("property", new Test(name = "John Doe"))
  property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("New Value: %s".format(evt.newValue))
  }

  val stringVisual = Visual[String]().label("String Visual").editing.required.validation(emptyValidator).build()
  stringVisual.property := "Hello World!"

  val bindingVisual = Visual[String]().label("Binding Visual").editing.bind(property, "name").build()

  body.contents += stringVisual
  body.contents += bindingVisual

  body.contents += new tag.Div {
    style.clear := Clear.Both

    contents += new tag.Button(content = "Toggle Editing") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => {
          stringVisual.editing := !stringVisual.editing()
          bindingVisual.editing := !bindingVisual.editing()
        }
      }
    }

    contents += new tag.Button(content = "Set Value") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => stringVisual.property := "Value Set!"
      }
    }
  }

  def emptyValidator(s: String) = if (s.isEmpty) {
    Right("Field must not be empty!")
  } else {
    Left(Some(s))
  }
}

case class Test(name: String)