package org.hyperscala.examples.ui

import org.hyperscala.examples.Example

import org.hyperscala.html._
import org.powerscala.Color

import org.hyperscala.jquery._
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

  $(canvas).drawArc(StrokeStyle(Color.Black), StrokeWidth(5), X(100), Y(100), Radius(50), Start(90), End(180)).send()
}
