package org.hyperscala.fabricjs.prop

import org.hyperscala.fabricjs.Object
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ObjectProperty[T](val name: String, val o: Object)(implicit manifest: Manifest[T]) extends Property[T](default = None)(o, manifest)