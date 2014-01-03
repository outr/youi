package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.Visual
import org.hyperscala.css.attributes._

import org.powerscala.property._
import org.powerscala.Country
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualExample extends Example {
  Webpage().require(Realtime)

  Webpage().body.style.fontFamily := "sans-serif"

  val property = Property[TestPerson](default = Some(new TestPerson(name = "John Doe", age = 21)))
  property.change.on {
    case evt => println("New Value: %s".format(evt.newValue))
  }

  val stringVisual = Visual[String]().label("String Visual").editing(true).required.validation(emptyValidator).build()
  stringVisual.property := "Hello World!"

  val bindingVisual = Visual[String]().label("Binding Visual").editing(true).bind(property, "name").build()

  val enumVisual = Visual[Country]().label("Country Visual").editing(true).build()
  enumVisual.property.change.on {
    case evt => println("Enum Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val booleanVisual = Visual[Boolean]().label("Boolean Visual").editing(true).build()
  booleanVisual.property.change.on {
    case evt => println("Boolean Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val dateVisual = Visual[Long]().visualType(new DateInputVisualType()).label("Date Visual").editing(true).build()
  dateVisual.property.change.on {
    case evt => println("Date Changed: %s - %s".format(evt.oldValue, evt.newValue))
  }

  val enumsVisual = Visual[List[Country]]().label("Countries Visual").editing(true).itemizedType[Country]().build()
  enumsVisual.property := List(Country.AI, Country.ZA)
  enumsVisual.property.change.on {
    case evt => println("Countries Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val stringsVisual = Visual[List[String]]().label("Strings Visual").editing(true).itemizedType[String] {
    case vb => vb.validation(emptyValidator)
  }.build()
  stringsVisual.property.change.on {
    case evt => println("String Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  val caseClassesVisual = Visual[List[TestPerson]]().label("Case Classes Visual").editing(true).itemizedType[TestPerson]().build()
  caseClassesVisual.property.change.on {
    case evt => println("Case Classes Changed: %s -> %s".format(evt.oldValue, evt.newValue))
  }

  contents.addAll(stringVisual, bindingVisual, enumVisual, booleanVisual, dateVisual, enumsVisual, stringsVisual, caseClassesVisual)

  contents += new tag.Div {
    style.clear := Clear.Both

    contents += new tag.Button(content = "Toggle Editing") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => {
          Visual.toggleEditing(VisualExample.this)
        }
      }
    }

    contents += new tag.Button(content = "Set Value") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => {
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