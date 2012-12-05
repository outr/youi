package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.{Visual, VisualBuilder}
import org.powerscala.property.StandardProperty
import org.hyperscala.ui.widgets.ListEditor

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ListSelectVisualType extends VisualType[List[_]] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[List[_]]

  def create(property: StandardProperty[List[_]], details: VisualBuilder[List[_]]) = {
    if (details.itemizedType == null) {
      throw new NullPointerException("'%s' with List as class type must define itemizedType for creation".format(details.name))
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
