package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.persistence.ValuePersistence
import tag.TextArea

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class TextAreaEditor[T](val property: StandardProperty[T])(implicit persistence: ValuePersistence[T], manifest: Manifest[T]) extends TextArea with ValueEditor[T] {
  val convert2String = (t: T) => persistence.toString(t, manifest.erasure)
  val convert2T = (s: String) => persistence.fromString(s, manifest.erasure)

  id := property.name()
  name := property.name()

  content.bindTo[T](property)(convert2String)
  property.bindTo[String](content)(convert2T)

  property.fireChanged()
}