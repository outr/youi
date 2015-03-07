package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Path(val path: String, name: String = "Path") extends Object(name) {
  lazy val minX = prop("minX", 0.0)
  lazy val minY = prop("minY", 0.0)

  override protected[fabricjs] def construct = s"new fabric.$name('$path', $props)"
}
