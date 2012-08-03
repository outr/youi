package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.data.ListSelect
import org.powerscala.reflect._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListSelectEditor[T](val property: StandardProperty[List[T]])(implicit val manifest: Manifest[T]) extends ListEditor[T] {
  lazy val valueEditor = new ListSelect[T](new StandardProperty[T]("%sField".format(property.name()), manifest.erasure.defaultForType.asInstanceOf[T]))
}