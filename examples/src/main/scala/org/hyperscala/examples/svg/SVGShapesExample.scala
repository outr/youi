package org.hyperscala.examples.svg

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.svg
import org.hyperscala.svg._
import org.hyperscala.svg.attributes._
import org.hyperscala.web.Webpage
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class SVGShapesExample extends Webpage with Example {
  val gradient = new svg.LinearGradient {
    id := "grad1"
    x1 := 0.pct
    y1 := 0.pct
    x2 := 100.pct
    y2 := 0.pct

    contents += new svg.Stop {
      offset := 0.pct
      style := "stop-color:rgb(255,255,0);stop-opacity:1"
    }
    contents += new svg.Stop {
      offset := 100.pct
      style := "stop-color:rgb(255,0,0);stop-opacity:1"
    }
  }
  val pattern = new svg.Pattern {
    id := "trianglePattern"
    x := 0.0
    y := 0.0
    width := 100.px
    height := 100.px
    viewBox := ViewBox(0.0, 0.0, 10.0, 10.0)
    patternUnits := PatternUnits.UserSpaceOnUse

    contents += new svg.Path {
      d := "M 0 0 L 7 0 L 3.5 7 z"
      fill := Color.Red
      stroke := Color.Blue
    }
  }

  body.contents += new svg.Svg {
    width := 800.px
    height := 600.px
    viewBox := ViewBox(0.0, 0.0, 1024.0, 768.0)

    contents += new svg.Defs {
      contents += gradient
      contents += pattern
    }

    contents += new svg.Polygon {
      fill := Color.Red
      stroke := Color.Blue
      strokeWidth := 10.0
      points := List(Point(350,75), Point(379,161), Point(469,161), Point(397,215),
                     Point(423,301), Point(350,250), Point(277,301), Point(303,215),
                     Point(231,161), Point(321,161))
      transform := List(Transform.scale(0.5, 0.5), Transform.translate(x = -200.0, y = -50.0))
    }
    contents += new svg.Polyline {
      fill := Paint.None
      stroke := Color.Blue
      strokeWidth := 10.0
      points := Point(50,375,
                      150,375,
                      150,325,
                      250,325,
                      250,375,
                      350,375,
                      350,250,
                      450,250,
                      450,375,
                      550,375,
                      550,175,
                      650,175,
                      650,375,
                      750,375,
                      750,100,
                      850,100,
                      850,375,
                      950,375,
                      950,25,
                      1050,25,
                      1050,375,
                      1150,375)
      transform := List(Transform.scale(0.4, 0.4))
    }
    contents += new svg.Ellipse {
      cx := 200.0
      cy := 70.0
      rx := 85.px
      ry := 55.px
      fill := Paint.Ref(gradient)
      transform := List(Transform.translate(x = 320.0, y = 120.0), Transform.rotate(-30.0))
    }
    contents += new svg.G {
      transform := List(Transform.translate(x = 480.0, y = 100.0), Transform.rotate(-30.0), Transform.scale(x = 0.5, y = 0.5))
      opacity := 0.5

      contents += new svg.Rect {
        x := 0.0
        y := 0.0
        width := 400.0
        height := 200.0
        rx := 50.0
        fill := Paint.None
        stroke := Color.Purple
        strokeWidth := 30.0
      }

      contents += new svg.Line {
        x1 := 50.0
        y1 := 50.0
        x2 := 350.0
        y2 := -50.0
        strokeWidth := 10.0
        stroke := Color.Green
      }
    }
    contents += new svg.Ellipse {
      fill := Paint.Ref(pattern)
      stroke := Color.Black
      strokeWidth := 5
      cx := 400.0
      cy := 300.0
      rx := 350.px
      ry := 150.px
    }
  }
}
