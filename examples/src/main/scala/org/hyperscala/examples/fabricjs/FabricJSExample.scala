package org.hyperscala.examples.fabricjs

import org.hyperscala.examples.Example
import org.hyperscala.fabricjs.{StaticCanvas, Rect, Canvas, FabricJS}
import org.hyperscala.html._
import org.powerscala.Color
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricJSExample extends Example {
  require(FabricJS)

  val t = new tag.Canvas(id = "myCanvas", width = 200, height = 200)
  t.style.border := "1px solid black"
  contents += t

  val canvas = new StaticCanvas(t)
  canvas.backgroundColor := Color.immutable(100, 100, 200, 255)   // TODO: make Alpha Double
  val rect = new Rect {
    left := 100.0
    top := 100.0
    fill := Color.Red
    width := 20.0
    height := 20.0
    originX := "center"
    originY := "center"
  }
  canvas.contents += rect

  contents += new tag.Button(content = "Rotate") {
    clickEvent.onRealtime {
      case evt => rect.angle := rect.angle() + 22.5
    }
  }
  contents += new tag.Button(content = "Remove") {
    clickEvent.onRealtime {
      case evt => canvas.contents -= rect
    }
  }
}