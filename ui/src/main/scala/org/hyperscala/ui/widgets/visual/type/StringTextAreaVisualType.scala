package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.StandardProperty
import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.site.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringTextAreaVisualType extends VisualType[String] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[String] && details.selection == null && details.multiLine

  def create(property: StandardProperty[String], details: VisualBuilder[String]) = new tag.TextArea {
    Webpage().require(Realtime)

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