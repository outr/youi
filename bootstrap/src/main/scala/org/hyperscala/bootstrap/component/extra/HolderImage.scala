package org.hyperscala.bootstrap.component.extra

import org.hyperscala.css.attributes.Length
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HolderImage(w: Int, h: Int, a: String = "") extends tag.Img(width = Length.Pixels(w), height = Length.Pixels(h), alt = a, clazz = List("img-thumbnail")) {
  data("src", s"holder.js/${w}x${h}")
}