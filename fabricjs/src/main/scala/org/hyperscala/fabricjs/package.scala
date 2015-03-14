package org.hyperscala

import org.hyperscala.fabricjs.paint.{Paint, ColorPaint}
import org.powerscala.Color

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object fabricjs {
  implicit def color2Paint(color: Color): Paint = ColorPaint(color)
}