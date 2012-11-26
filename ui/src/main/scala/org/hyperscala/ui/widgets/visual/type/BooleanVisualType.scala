package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.VisualBuilder
import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.ListSelect
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.site.realtime.Realtime
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
object BooleanVisualType extends VisualType[Boolean] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[Boolean]

  def create(property: StandardProperty[Boolean], details: VisualBuilder[Boolean]) = {
    new ListSelect[Boolean](List(true, false)) {
      Webpage().require(Realtime)

      event.change := JavaScriptEvent()

      property.onChange {
        updateSelect()
      }

      selected.onChange {
        updateProperty()
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
