package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.{EnumEntry, Enumerated}
import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.visual.VisualBuilder

import org.powerscala.reflect._
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object EnumEntryVisualType extends VisualType[EnumEntry[_]] {
  def valid(details: VisualBuilder[_]) = details.clazz.hasType(classOf[EnumEntry[_]])

  def create(property: StandardProperty[EnumEntry[_]], details: VisualBuilder[EnumEntry[_]]) = {
    val enumerated = details.clazz.instance.get.asInstanceOf[Enumerated[_]]
    val nullAllowed = details.nullAllowed
    val list = new ListSelect[Any](enumerated.values, nullAllowed = nullAllowed) {
      Webpage().require(Realtime)

      event.change := JavaScriptEvent()

      property.onChange {
        updateSelect()
      }

      selected.onChange {
        updateProperty()
      }

      def updateSelect() = selected := List(property().asInstanceOf[Any])

      def updateProperty() = property := selected().headOption.getOrElse(null).asInstanceOf[EnumEntry[_]]
    }
    if (details.default.nonEmpty) {
      list.selected := List(details.default.get.asInstanceOf[Any])
    }
    list
  }
}
