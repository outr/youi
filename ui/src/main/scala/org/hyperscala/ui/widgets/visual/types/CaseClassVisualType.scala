package org.hyperscala.ui.widgets.visual.types

import org.powerscala.property.Property
import org.hyperscala.ui.widgets.visual.{Visualize, VisualBuilder}

import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object CaseClassVisualType extends VisualType[AnyRef] {
  def valid(details: VisualBuilder[_]) = details.clazz.isCase && details.selection == null

  def create(property: Property[AnyRef], details: VisualBuilder[AnyRef]) = {
    property.changing.on {
      case newValue => if (newValue == null) {
        Some(details.clazz.create[AnyRef](Map.empty))    // Keep null from ever being set
      } else {
        Some(newValue)
      }
    }
    if (property() == null) {
      property := details.clazz.create[Any](Map.empty).asInstanceOf[AnyRef]
    }
    Visualize(_labeled = true, _editing = true).clazz[AnyRef](bindProperty = property)(Manifest.classType[AnyRef](details.clazz)).build()
  }
}
