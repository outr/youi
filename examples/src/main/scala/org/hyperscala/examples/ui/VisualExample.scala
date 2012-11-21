package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.Visual
import org.hyperscala.css.attributes._

import org.powerscala.property._
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.site.realtime.RealtimeWebpage
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import org.powerscala.Country
import org.hyperscala.ui.widgets.visual.`type`.DateInputVisualType

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

  val enumVisual = Visual[Country]().label("Country Visual").editing.build()
  enumVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Enum Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val booleanVisual = Visual[Boolean]().label("Boolean Visual").editing.build()
  booleanVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Boolean Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val dateVisual = Visual[Long]().visualType(new DateInputVisualType()).label("Date Visual").editing.build()
  dateVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Date Changed: %s - %s".format(evt.oldValue, evt.newValue))
  }

  val enumsVisual = Visual[List[Country]]().label("Countries Visual").editing.itemizedType[Country].build()
  enumsVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Countries Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  // TODO: add List of Strings
  // TODO: add List of case classes

  body.contents.addAll(stringVisual, bindingVisual, enumVisual, booleanVisual, dateVisual, enumsVisual)

  body.contents += new tag.Div {
    style.clear := Clear.Both

    contents += new tag.Button(content = "Toggle Editing") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => {
          Visual.toggleEditing(body)
        }
      }
    }

    contents += new tag.Button(content = "Set Value") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => {
          stringVisual.property := "Value Set!"
          enumVisual.property := Country.KZ
        }
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