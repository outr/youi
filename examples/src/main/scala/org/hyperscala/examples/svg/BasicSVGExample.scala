package org.hyperscala.examples.svg

import org.hyperscala.examples.Example
import org.hyperscala.svg
import org.hyperscala.svg._
import org.powerscala.Color
import svg.attributes.{Transform, FontWeight}
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class BasicSVGExample extends Example {
  contents += new svg.Svg {
    width := 800.px

    contents += new svg.Circle {
      cx := 100
      cy := 50
      r := 40
      stroke := Color.Black
      strokeWidth := 2
      fill := Color.Red
    }
    contents += new svg.Text {
      x := 200
      y := 55
      fontWeight := FontWeight.Bold
      fontSize := 18.pt
      fill := Color.Blue
      content := "Hello World!"
    }
    contents += new svg.Line {
      x1 := 200.0
      x2 := 335.0
      y1 := 75.0
      y2 := 75.0
      stroke := Color.Green
      strokeWidth := 2.5
    }
    contents += new svg.G {
      transform := List(Transform.translate(150.0))

      contents += new svg.Rect {
        width := 400.0
        height := 100.0
        rx := 50.0
        fill := Color.Black
        opacity := 0.5
      }
    }
  }

  def sourceURL = "https://github.com/darkfrog26/hyperscala/blob/master/examples/src/main/scala/org/hyperscala/examples/svg/BasicSVGExample.scala"
}
