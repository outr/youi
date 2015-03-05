package org.hyperscala.fabricjs

import org.hyperscala.html.tag

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Canvas(canvas: tag.Canvas) extends StaticCanvas(canvas) {
  override protected def className = "Canvas"
}