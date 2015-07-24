package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.dsl._
import org.hyperscala.jquery.jcanvas._
import org.hyperscala.web._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jCanvasExample extends Webpage with Example {
  require(jCanvas)

  val canvas = new tag.Canvas(id = "canvas", width = 600, height = 300) {
    style.backgroundColor := Color.LightBlue
  }
  body.contents += canvas

  eval($(canvas).drawArc(strokeStyle = Color.Black, strokeWidth = 5, x = 100, y = 100, radius = 50, start = 90, end = 180))
}
