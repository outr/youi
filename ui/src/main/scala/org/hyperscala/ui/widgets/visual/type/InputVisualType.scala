package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.visual.{Stringify, VisualDetails}
import org.hyperscala.html.tag
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.site.realtime.Realtime
import org.hyperscala.html.attributes.InputType
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait InputVisualType[T] extends VisualType[T] with Stringify[T] {
  def fromString(s: String): T

  def create(property: StandardProperty[T], details: VisualDetails[T]): BodyChild = new tag.Input {
    Webpage().require(Realtime)

    if (details.masked) {
      inputType := InputType.Password
    } else {
      inputType := InputType.Text
    }

    event.change := JavaScriptEvent()

    property.onChange {
      updateInput()
    }

    value.onChange {
      updateProperty()
    }

    def updateInput() = value := InputVisualType.this.toString(property())

    def updateProperty() = property := InputVisualType.this.fromString(value())
  }
}
