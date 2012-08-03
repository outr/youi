package org.hyperscala.editor

import org.powerscala.property.StandardProperty

import org.powerscala.reflect._
import org.powerscala.{EnumEntry, Enumerated}
import org.hyperscala.data.ListSelect

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class EnumEntryEditor[T](property: StandardProperty[T])(implicit manifest: Manifest[T])
    extends ListSelect[T](property, identifier = (t: T) => t.asInstanceOf[EnumEntry[_]].name()) with ValueEditor[T] {
  val companion = manifest.erasure.companion.getOrElse(throw new RuntimeException("No companion class for %s".format(manifest.erasure.getName))).instance.get
  name := property.name()
  values := companion.asInstanceOf[Enumerated[_]].values.toList.asInstanceOf[List[T]]
}