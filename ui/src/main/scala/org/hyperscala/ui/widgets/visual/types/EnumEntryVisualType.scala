package org.hyperscala.ui.widgets.visual.types

import org.powerscala.enum.{EnumEntry, Enumerated}
import org.powerscala.property.Property
import org.hyperscala.ui.widgets.visual.VisualBuilder

import org.powerscala.reflect._
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.web._
import org.hyperscala.realtime.{RealtimeEvent, Realtime}

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object EnumEntryVisualType extends VisualType[EnumEntry] {
  def valid(details: VisualBuilder[_]) = details.clazz.hasType(classOf[EnumEntry])

  def create(property: Property[EnumEntry], details: VisualBuilder[EnumEntry]) = {
    val enumerated = details.clazz.instance.get.asInstanceOf[Enumerated[_]]
    val nullAllowed = details.nullAllowed
    val list = new ListSelect[Any](enumerated.values, nullAllowed = nullAllowed) {
      this.require(Realtime)

      select.changeEvent := RealtimeEvent()

      property.change.on {
        case evt => updateSelect()
      }

      selected.change.on {
        case evt => updateProperty()
      }

      def updateSelect() = selected := List(property().asInstanceOf[Any])

      def updateProperty() = property := selected().headOption.getOrElse(null).asInstanceOf[EnumEntry]
    }
    if (details.default.nonEmpty) {
      list.selected := List(details.default.get.asInstanceOf[Any])
    }
    list
  }
}
