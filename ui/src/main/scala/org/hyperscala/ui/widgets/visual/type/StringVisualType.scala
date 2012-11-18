package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.StandardProperty
import org.hyperscala.html.attributes.InputType
import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.VisualDetails
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.site.realtime.Realtime

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object StringVisualType extends VisualType[String] {
  def valid(details: VisualDetails[_]) = details.clazz == classOf[String] && details.selection.isEmpty

  def create(property: StandardProperty[String], details: VisualDetails[String]) = new tag.Input {
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

    def updateInput() = value := property()

    def updateProperty() = property := value()
  }
}
