package org.hyperscala.ui.widgets.visual

import `type`.VisualType
import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class VisualBuilder[T](labeled: Boolean = true,
                            label: String = null,
                            editable: Boolean = true,
                            editing: Boolean = false,
                            selection: List[T] = null,
                            default: Option[T] = None,
                            valueType: Class[_] = null,
                            masked: Boolean = false,
                            validations: List[T => Option[T]] = Nil,
                            visualType: Option[VisualType[T]] = None,
                            bindProperty: StandardProperty[_] = null,
                            bindHierarchy: String = null)
                           (implicit val manifest: Manifest[T]) extends VisualDetails[T] {
  def clazz = manifest.erasure
}