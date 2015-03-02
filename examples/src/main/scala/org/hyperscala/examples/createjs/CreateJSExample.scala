package org.hyperscala.examples.createjs

import org.hyperscala.createjs.CreateJS
import org.hyperscala.easeljs.{ShapeProperty, Stage, EaselJS}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.tweenjs.{Ticker, Ease, Tween}
import org.powerscala.Color

/**
 * Matt Hicks <matt@outr.com>
 */
class CreateJSExample extends Example {
  require(CreateJS)

  val canvas = new tag.Canvas(id = "testCanvas", width = 500, height = 200)
  contents += canvas

  val stage = Stage(canvas)
  val circle = stage.createShape()
  circle.graphics.beginFill(Color.DeepSkyBlue).drawCircle(0.0, 0.0, 50.0)
  circle.x := 100.0
  circle.y := 100.0

  import ShapeProperty._
  val tween = Tween(circle, loop = true)
    .to(List(X.value(400.0)), 1000, Ease.PowInOut(4.0))
    .to(List(Alpha.value(0.0), Y.value(75.0)), 500, Ease.PowInOut(2.0))
    .to(List(Alpha.value(0.0), Y.value(125.0)), 100)
    .to(List(Alpha.value(1.0), Y.value(100.0)), 500, Ease.PowInOut(2.0))
    .to(List(X.value(100.0)), 800, Ease.PowInOut(2.0))

  Ticker.setFPS(stage, 60)
  Ticker.addEventListener("tick", stage)
}