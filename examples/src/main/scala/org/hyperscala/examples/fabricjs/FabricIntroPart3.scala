package org.hyperscala.examples.fabricjs

import org.hyperscala.bootstrap.component.Button
import org.hyperscala.fabricjs._
import org.hyperscala.fabricjs.prop.Adjust
import org.hyperscala.realtime._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricIntroPart3 extends FabricIntroExample {
  first()
  second()

  def first() = {
    val canvas = Canvas(canvases, 200, 200)

    val circle = new Circle {
      radius := 100.0
      fill := Color.immutable("#eef")
      scaleY := 0.5
      originX := "center"
      originY := "center"
      basic()
    }

    val text = new Text("hello world") {
      fontSize := 30.0
      originX := "center"
      originY := "center"
      basic()
    }

    val group = new Group {
      angle := -10.0
      originX := "center"
      originY := "center"
    }
    group.contents.addAll(circle, text)

    canvas.contents += group

    // Unfortunately, a temporary work-around for a bug in FabricJS requires the items to be added before setting positional properties
    group.left := 100.0
    group.top := 100.0
    group.setCoords()

    buttons.contents += new Button("Modify") {
      clickEvent.onRealtime {
        case evt => {
          circle.fill := Color.Red
          text.text := "trololo"
          text.fill := Color.White
        }
      }
    }
  }

  def second() = {
    val canvas = Canvas(canvases, 200, 200)

    val img1 = new Image("/images/pug_small.jpg") {
      scaleX := 0.1
      scaleY := 0.1
      left := 10.0
      top := 10.0
    }

    val img2 = new Image("/images/pug_small.jpg") {
      scaleX := 0.1
      scaleY := 0.1
      left := 60.0
      top := 60.0
    }

    val img3 = new Image("/images/pug_small.jpg") {
      scaleX := 0.1
      scaleY := 0.1
      left := 110.0
      top := 110.0
    }

    val group = new Group

    group.contents.addAll(img1, img2, img3)

    canvas.contents += group

    group.originX := "center"
    group.originY := "center"

    buttons.contents += new Button("Rotate") {
      clickEvent.onRealtime {
        case evt => {
          group.angle.animate(Adjust += 180.0, duration = 2.0)
        }
      }
    }
  }
}
