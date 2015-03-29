package org.hyperscala.examples.fabricjs

import org.hyperscala.examples.Example
import org.hyperscala.fabricjs._
import org.hyperscala.fabricjs.filters.{Brightness, Grayscale}
import org.hyperscala.fabricjs.paint.{ColorStop, LinearGradient}
import org.hyperscala.fabricjs.prop.Adjust
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.Color
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricJSExample extends Example {
  require(FabricJS)

  val t = new tag.Canvas(id = "myCanvas", width = 600, height = 200)
  t.style.border := "1px solid black"
  contents += t

  val canvas = new Canvas(t) {
    selection := false
  }
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

  val image = new Image("/images/hyperscala.png") {
    left := 10.0
    top := 100.0
    width := 124.0
    height := 45.0
    angle := -45.0
    filters.contents += Grayscale
  }
  canvas.contents += image

  val path = new Path("M 0 0 L 200 100 L 170 200 z") {
    left := 200.0
    originX := "center"
    originY := "center"
    fill := Color.Red
    strokeWidth := 5.0
    stroke := Color.Green
    opacity := 0.5
  }
  canvas.contents += path

  val circle = new Circle {
    left := 100.0
    top := 100.0
    radius := 50.0
    basic()
    fill := LinearGradient(0.0, 0.0, 100.0, 0.0, List(ColorStop(0.0, Color.Red), ColorStop(0.2, Color.Orange), ColorStop(0.4, Color.Yellow), ColorStop(0.6, Color.Green), ColorStop(0.8, Color.Blue), ColorStop(1.0, Color.Purple)))
    mouseUpEvent := JavaScriptString("alert('Circle clicked!');")
  }
  canvas.contents += circle

  val text = new Text("Hello World!") {
    left := 250.0
    top := 50.0
    fontFamily := "sans-serif"
    shadow := "rgba(0,0,0,0.3) 5px 5px 5px"
  }
  canvas.contents += text

  contents += new tag.Div {
    contents += new tag.Button(content = "Rotate") {
      clickEvent.onRealtime {
        case evt => {
          rect.angle.animate(Adjust += 180.0, duration = 1.0)
        }
      }
    }
    contents += new tag.Button(content = "Remove") {
      clickEvent.onRealtime {
        case evt => canvas.contents -= rect
      }
    }

    val brightness = Brightness(255)
  }
}