package org.hyperscala.ui.widgets.visual.types

import org.powerscala.property.Property
import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.{RealtimeEvent, Realtime}

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringTextAreaVisualType extends VisualType[String] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[String] && details.selection == null && details.multiLine

  def create(property: Property[String], details: VisualBuilder[String]) = new tag.TextArea {
    Webpage().require(Realtime)

    changeEvent := RealtimeEvent()

    property.change.on {
      case evt => updateInput()
    }

    value.change.on {
      case evt => updateProperty()
    }

    def updateInput() = value := property()

    def updateProperty() = property := value()
  }
}