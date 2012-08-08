package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.bean.{BeanContainer, BeanDiv}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListBeanEditor[T](val parentContainer: BeanContainer[_], val property: StandardProperty[List[T]], override val default: T)(implicit val manifest: Manifest[T]) extends ListEditor[T] {
  lazy val valueEditor = new BeanDiv[T](property.name(), parentContainer, default)(manifest)
}
