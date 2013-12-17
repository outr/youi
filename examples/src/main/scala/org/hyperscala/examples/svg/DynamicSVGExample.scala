package org.hyperscala.examples.svg

import org.hyperscala.examples.Example
import org.hyperscala.svg
import org.hyperscala.svg._
import org.powerscala.Color
import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.{RealtimeEvent, Realtime}

import language.reflectiveCalls
import java.util.Random

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicSVGExample extends Example {
  val r = new Random()
  Webpage().require(Realtime)

  val canvasWidth = 850
  val canvasHeight = 400

  val canvas = new svg.Svg(id = "canvas", width = canvasWidth.px, height = canvasHeight.px) {
    style.backgroundColor := Color.Yellow
  }
  contents += canvas

  contents += new tag.Button(content = "Add Circle") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => addCircle()
    }
  }

  def addCircle() = {
    canvas.contents += new svg.Circle(cx = r.nextInt(canvasWidth - 100) + 50, cy = r.nextInt(canvasHeight - 100) + 50, r = 50.0) {
      stroke := Color.Black
      strokeWidth := 1.0
      fill := Color.random
    }
  }
}
