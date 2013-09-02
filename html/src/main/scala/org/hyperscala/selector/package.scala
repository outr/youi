package org.hyperscala

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object selector {
  implicit def tag2Selector(tag: IdentifiableTag) = TagIdSelector(tag)
}
