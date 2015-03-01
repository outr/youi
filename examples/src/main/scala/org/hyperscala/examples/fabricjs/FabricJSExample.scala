package org.hyperscala.examples.fabricjs

import org.hyperscala.examples.Example
import org.hyperscala.fabricjs.{Rect, Canvas, FabricJS}
import org.hyperscala.html._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricJSExample extends Example {
  require(FabricJS)

  val t = new tag.Canvas(id = "myCanvas", width = 200, height = 200)
  t.style.border := "1px solid black"
  contents += t

  val canvas = new Canvas(t)
  val rect = new Rect {
    left := 100.0
    top := 100.0
    fill := Color.Red
    width := 20.0
    height := 20.0
  }
  canvas.add(rect)
}