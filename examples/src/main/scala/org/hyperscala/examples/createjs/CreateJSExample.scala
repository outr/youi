package org.hyperscala.examples.createjs

import org.hyperscala.createjs.CreateJS
import org.hyperscala.easeljs.{Stage, EaselJS}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.powerscala.Color

/**
 * Matt Hicks <matt@outr.com>
 */
class CreateJSExample extends Example {
  require(CreateJS)

  val canvas = new tag.Canvas(id = "testCanvas", width = 500, height = 300)
  contents += canvas

  val stage = Stage(canvas)
  val circle = stage.createShape()
  circle.graphics.beginFill(Color.DeepSkyBlue).drawCircle(0.0, 0.0, 50.0)
  circle.x := 100.0
  circle.y := 100.0

  stage.update()
}
