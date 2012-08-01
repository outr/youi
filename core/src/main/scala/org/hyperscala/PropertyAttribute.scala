package org.hyperscala

import org.powerscala.property.{PropertyParent, StandardProperty}
import org.powerscala.property.backing.{VariableBacking, Backing}
import persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class PropertyAttribute[T](_name: String, default: T, exclude: Boolean = false, backing: Backing[T] = new VariableBacking[T])
                             (implicit persister: ValuePersistence[T], parent: PropertyParent, val manifest: Manifest[T])
                             extends StandardProperty[T](_name, default, backing)(parent) with XMLAttribute {
  def attributeValue = persister.toString(value, manifest.erasure)

  def attributeValue_=(value: String) = this := persister.fromString(value, manifest.erasure)

  def shouldRender = {
    modified &&
      !exclude &&
      (value == true || (attributeValue != null && attributeValue.length > 0)) // Keep empty attributes out
  }
}

object PropertyAttribute {
  def apply[T](name: String, default: T, exclude: Boolean = false, backing: Backing[T] = new VariableBacking[T])
              (implicit persister: ValuePersistence[T], parent: PropertyParent, manifest: Manifest[T]) = {
    new PropertyAttribute[T](name, default, exclude, backing)(persister, parent, manifest)
  }
}