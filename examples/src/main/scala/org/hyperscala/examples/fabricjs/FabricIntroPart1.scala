package org.hyperscala.examples.fabricjs

import org.hyperscala.bootstrap.component.Button
import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes.{LineStyle, Float}
import org.hyperscala.examples.Example
import org.hyperscala.fabricjs._
import org.hyperscala.selector.Selector
import org.powerscala.Color
import org.hyperscala.html._
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricIntroPart1 extends FabricIntroExample {
  first()
  second()
  third()
  fourth()
  fifth()
  sixth()
  seventh()
  eighth()
  ninth()

  def first() = {
    val canvas = Canvas(canvases, 200, 200)

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
  }

  def second() = {
    val canvas = Canvas(canvases, 200, 200)

    val rect = new Rect {
      left := 100.0
      top := 100.0
      fill := Color.Red
      width := 20.0
      height := 20.0
      angle := 45.0
      originX := "center"
      originY := "center"
    }

    canvas.contents += rect
  }

  def third() = {
    val canvas = Canvas(canvases, 200, 200)

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

    buttons.contents += new Button("Move Rectangle") {
      clickEvent.onRealtime {
        case evt => {
          rect.left := 20.0
          rect.top := 50.0
        }
      }
    }
  }

  def fourth() = {
    val canvas = Canvas(canvases, 200, 200)

    val circle = new Circle {
      radius := 20.0
      fill := Color.Green
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
    }

    val triangle = new Triangle {
      width := 20.0
      height := 30.0
      fill := Color.Blue
      left := 50.0
      top := 50.0
      originX := "center"
      originY := "center"
    }

    canvas.contents.addAll(circle, triangle)
  }

  def fifth() = {
    val canvas = Canvas(canvases, 200, 200)

    val rect = new Rect {
      left := 100.0
      top := 100.0
      fill := Color.Black
      width := 50.0
      height := 50.0
      originX := "center"
      originY := "center"
    }

    canvas.contents += rect

    buttons.contents += new Button("Manipulate Object") {
      clickEvent.onRealtime {
        case evt => {
          rect.fill := Color.Red
          rect.strokeWidth := 5.0
          rect.stroke := Color.immutable(100, 200, 200, 0.5)
          rect.angle := 15.0
          rect.flipY := true
        }
      }
    }
  }

  def sixth() = {
    val canvas = Canvas(canvases, 200, 200)

    val image = new Image("/images/pug_small.jpg") {
      left := 100.0
      top := 100.0
      angle := 30.0
      opacity := 0.85
      originX := "center"
      originY := "center"
      scaleX := 0.3
      scaleY := 0.3
    }

    canvas.contents += image
  }

  def seventh() = {
    val canvas = Canvas(canvases, 200, 200)

    val path = new Path("M 0 0 L 200 100 L 170 200 z") {
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      scaleX := 0.75
      scaleY := 0.75
    }

    canvas.contents += path
  }

  def eighth() = {
    val canvas = Canvas(canvases, 200, 200)

    val path = new Path("M 0 0 L 300 100 L 200 300 z") {
      left := 100.0
      top := 100.0
      fill := Color.Red
      stroke := Color.Green
      strokeWidth := 10.0
      opacity := 0.5
      originX := "center"
      originY := "center"
      scaleX := 0.5
      scaleY := 0.5
    }

    canvas.contents += path
  }

  def ninth() = {
    val canvas = Canvas(canvases, 200, 200)

    val path = new Path("M121.32,0L44.58,0C36.67,0,29.5,3.22,24.31,8.41c-5.19,5.19-8.41,12.37-8.41,20.28c0,15.82,12.87,28.69,28.69,28.69c0,0,4.4,0,7.48,0C36.66,72.78,8.4,101.04,8.4,101.04C2.98,106.45,0,113.66,0,121.32c0,7.66,2.98,14.87,8.4,20.29l0,0c5.42,5.42,12.62,8.4,20.28,8.4c7.66,0,14.87-2.98,20.29-8.4c0,0,28.26-28.25,43.66-43.66c0,3.08,0,7.48,0,7.48c0,15.82,12.87,28.69,28.69,28.69c7.66,0,14.87-2.99,20.29-8.4c5.42-5.42,8.4-12.62,8.4-20.28l0-76.74c0-7.66-2.98-14.87-8.4-20.29C136.19,2.98,128.98,0,121.32,0z") {
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      scaleX := 0.75
      scaleY := 0.75
    }

    canvas.contents += path
  }
}
