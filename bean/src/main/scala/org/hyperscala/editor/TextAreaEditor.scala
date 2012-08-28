package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.html._
import org.powerscala.hierarchy.event.ChildAddedEvent
import tag.TextArea

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class TextAreaEditor[T](val property: StandardProperty[T])(implicit persistence: ValuePersistence[T], manifest: Manifest[T]) extends TextArea with ValueEditor[T] {
  val convert2String = (t: T) => persistence.toString(t, manifest.erasure)
  val convert2T = (s: String) => persistence.fromString(s, manifest.erasure)

  name := property.name()

  listeners.synchronous {
    case evt: ChildAddedEvent => property := convert2T(toXML.getTextTrim)
  }

//  value.bindTo[T](property)(convert2String)
//  property.bindTo[String](value)(convert2T)
  property.onChange {
    contents.replaceWith(convert2String(property()))
  }

  property.fireChanged()
}