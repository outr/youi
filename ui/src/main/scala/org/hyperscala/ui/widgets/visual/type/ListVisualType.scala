package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.web.site.Webpage
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.realtime.Realtime

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ListVisualType extends VisualType[Any] {
  def valid(details: VisualBuilder[_]) = details.selection != null

  def create(property: StandardProperty[Any], details: VisualBuilder[Any]) = {
    val list = new ListSelect[Any](values = details.selection, nullAllowed = details.nullAllowed) {
      Webpage().require(Realtime)

      event.change := JavaScriptEvent()

      property.onChange {
        updateSelect()
      }
      selected.onChange {
        updateProperty()
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
