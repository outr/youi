package org.hyperscala.ui.widgets.visual.types

import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.powerscala.property.Property
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.realtime.RealtimeEvent

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object BooleanVisualType extends VisualType[Boolean] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[Boolean]

  def create(property: Property[Boolean], details: VisualBuilder[Boolean]) = {
    new ListSelect[Boolean](List(true, false)) {
      changeEvent := RealtimeEvent()

      property.change.on {
        case evt => updateSelect()
      }

      selected.change.on {
        case evt => updateProperty()
      }

      override def toString(item: Boolean) = item match {
        case true => "Yes"
        case false => "No"
      }

      def updateSelect() = selected := List(property())

      def updateProperty() = property := selected().headOption.getOrElse(false)
    }
  }
}
