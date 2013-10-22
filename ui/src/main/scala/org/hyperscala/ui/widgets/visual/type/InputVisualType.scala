package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.Property
import org.hyperscala.ui.widgets.visual.{Stringify, VisualBuilder}
import org.hyperscala.html.tag
import org.hyperscala.web.Webpage
import org.hyperscala.html.attributes.InputType
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.realtime.{RealtimeEvent, Realtime}

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait InputVisualType[T] extends VisualType[T] with Stringify[T] {
  def fromString(s: String): T

  def create(property: Property[T], details: VisualBuilder[T]): BodyChild = new tag.Input {
    Webpage().require(Realtime)

    if (details.masked) {
      inputType := InputType.Password
    } else {
      inputType := InputType.Text
    }

    changeEvent := RealtimeEvent()

    property.change.on {
      case evt => updateInput()
    }

    value.change.on {
      case evt => updateProperty()
    }

    def updateInput() = value := InputVisualType.this.toString(property())

    def updateProperty() = property := InputVisualType.this.fromString(value())
  }
}
