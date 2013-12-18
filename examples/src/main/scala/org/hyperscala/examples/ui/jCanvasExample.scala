package org.hyperscala.examples.ui

import org.hyperscala.examples.Example

import org.hyperscala.html._
import org.powerscala.Color

import org.hyperscala.jquery.jcanvas._
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jCanvasExample extends Example {
  val canvas = new tag.Canvas(id = "canvas", width = 600, height = 300) {
    style.backgroundColor := Color.LightBlue
  }
  contents += canvas

  $(canvas).drawArc(strokeStyle = Color.Black, strokeWidth = 5, x = 100, y = 100, radius = 50, start = 90, end = 180).send()
}
