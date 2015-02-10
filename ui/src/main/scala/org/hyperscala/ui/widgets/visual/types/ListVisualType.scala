package org.hyperscala.ui.widgets.visual.types

import org.powerscala.property.Property
import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.web._
import org.hyperscala.realtime.{RealtimeEvent, Realtime}

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ListVisualType extends VisualType[Any] {
  def valid(details: VisualBuilder[_]) = details.selection != null

  def create(property: Property[Any], details: VisualBuilder[Any]) = {
    val list = new ListSelect[Any](values = details.selection, nullAllowed = details.nullAllowed) {
      this.require(Realtime)

      select.changeEvent := RealtimeEvent()

      property.change.on {
        case evt => updateSelect()
      }
      selected.change.on {
        case evt => updateProperty()
      }

      def updateSelect() = selected := List(property())
      def updateProperty() = property := selected().headOption.getOrElse(null).asInstanceOf[AnyRef]
    }
    if (details.default.nonEmpty) {
      list.selected := List(details.default.get)
    }
    list
  }
}
