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

  val property = Property[TestPerson]("property", new TestPerson(name = "John Doe", age = 21))
  property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("New Value: %s".format(evt.newValue))
  }

  val stringVisual = Visual[String]().label("String Visual").editing(true).required.validation(emptyValidator).build()
  stringVisual.property := "Hello World!"

  val bindingVisual = Visual[String]().label("Binding Visual").editing(true).bind(property, "name").build()

  val enumVisual = Visual[Country]().label("Country Visual").editing(true).build()
  enumVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Enum Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val booleanVisual = Visual[Boolean]().label("Boolean Visual").editing(true).build()
  booleanVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Boolean Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val dateVisual = Visual[Long]().visualType(new DateInputVisualType()).label("Date Visual").editing(true).build()
  dateVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Date Changed: %s - %s".format(evt.oldValue, evt.newValue))
  }

  val enumsVisual = Visual[List[Country]]().label("Countries Visual").editing(true).itemizedType[Country]().build()
  enumsVisual.property := List(Country.AI, Country.ZA)
  enumsVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Countries Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val stringsVisual = Visual[List[String]]().label("Strings Visual").editing(true).itemizedType[String] {
    case vb => vb.validation(emptyValidator)
  }.build()
  stringsVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("String Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val caseClassesVisual = Visual[List[TestPerson]]().label("Case Classes Visual").editing(true).itemizedType[TestPerson]().build()
  caseClassesVisual.property.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Case Classes Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  body.contents.addAll(stringVisual, bindingVisual, enumVisual, booleanVisual, dateVisual, enumsVisual, stringsVisual, caseClassesVisual)

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

case class TestPerson(name: String, age: Int)