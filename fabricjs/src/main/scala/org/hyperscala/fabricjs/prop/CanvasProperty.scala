package org.hyperscala.fabricjs.prop

import org.hyperscala.fabricjs.StaticCanvas
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CanvasProperty[T](val name: String, val c: StaticCanvas)(implicit manifest: Manifest[T]) extends Property[T](default = None)(c, manifest)