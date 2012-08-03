package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.bean.BeanDiv

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListBeanEditor[T](val property: StandardProperty[List[T]], override val default: T)(implicit val manifest: Manifest[T]) extends ListEditor[T] {
  lazy val valueEditor = new BeanDiv[T](null, default)(manifest)
}
