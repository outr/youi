package org.hyperscala.ui.widgets.visual.`type`

import org.powerscala.property.Property
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.ui.widgets.visual.VisualBuilder

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait VisualType[T] {
  def valid(details: VisualBuilder[_]): Boolean

  def create(property: Property[T], details: VisualBuilder[T]): BodyChild
}

object VisualType {
  private var _list = List[VisualType[_]](ListVisualType,
                                          StringInputVisualType,
                                          StringTextAreaVisualType,
                                          EnumEntryVisualType,
                                          BooleanVisualType,
                                          IntInputVisualType,
                                          FloatInputVisualType,
                                          ListSelectVisualType,
                                          CaseClassVisualType,
                                          DateInputVisualType)

  def +=(vt: VisualType[_]) = synchronized {
    _list = vt :: _list
  }

  def apply[T](property: Property[T], details: VisualBuilder[T]) = _list.collectFirst {
    case t if (t.valid(details)) => t.asInstanceOf[VisualType[T]].create(property, details)
  }
}