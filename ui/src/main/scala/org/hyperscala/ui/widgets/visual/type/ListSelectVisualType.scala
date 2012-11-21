package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.{Visual, VisualDetails}
import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.ListEditor

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ListSelectVisualType extends VisualType[List[_]] {
  def valid(details: VisualDetails[_]) = details.clazz == classOf[List[_]]

  def create(property: StandardProperty[List[_]], details: VisualDetails[List[_]]) = {
    if (details.itemizedType == null) {
      throw new NullPointerException("%s with List as class type must define itemizedType for creation".format(details))
    }

    new ListEditor[Any] {
      def manifest = details.itemizedType.manifest.asInstanceOf[Manifest[Any]]

      def visual = editor.asInstanceOf[Visual[_]]

      def createEditor() = details.itemizedType.labeled(l = false).editing.build()

      property bind list
      list bind property

      current bind visual.property
      visual.property bind current
    }
  }
}
