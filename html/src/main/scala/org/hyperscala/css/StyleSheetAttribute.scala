package org.hyperscala.css

import org.powerscala.property.backing.{VariableBacking, Backing}
import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class StyleSheetAttribute[T](_name: String, default: T, exclude: Boolean = false, backing: Backing[T] = new VariableBacking[T])
                         (implicit persister: ValuePersistence[T], parent: StyleSheet, manifest: Manifest[T])
                         extends PropertyAttribute[T](_name, default, exclude, backing)(persister, parent, manifest)

object StyleSheetAttribute {
  def apply[T](name: String, default: T, exclude: Boolean = false, backing: Backing[T] = new VariableBacking[T])
              (implicit persister: ValuePersistence[T], parent: StyleSheet, manifest: Manifest[T]) = {
    new StyleSheetAttribute[T](name, default, exclude, backing)(persister, parent, manifest)
  }
}
