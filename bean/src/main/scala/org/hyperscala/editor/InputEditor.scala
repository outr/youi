package org.hyperscala.editor

import org.hyperscala.persistence.ValuePersistence
import org.powerscala.property.StandardProperty
import org.hyperscala.html.attributes.AutoComplete
import org.hyperscala.html.tag.Input

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class InputEditor[T](val property: StandardProperty[T])(implicit persistence: ValuePersistence[T], manifest: Manifest[T]) extends Input with ValueEditor[T] {
  val convert2String = (t: T) => persistence.toString(t, manifest.erasure)
  val convert2T = (s: String) => persistence.fromString(s, manifest.erasure)

  id := property.name()
  name := property.name()
  autoComplete := AutoComplete.Off     // Make sure the field is correct every time

  value.bindTo[T](property)(convert2String)
  property.bindTo[String](value)(convert2T)

  property.fireChanged()
}