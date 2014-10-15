package org.hyperscala.bootstrap.component

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HolderImage(w: Int, h: Int, a: String = "") extends tag.Img(width = w.toString, height = h.toString, alt = a, clazz = List("img-thumbnail")) {
  data("src", s"holder.js/${w}x${h}")
}