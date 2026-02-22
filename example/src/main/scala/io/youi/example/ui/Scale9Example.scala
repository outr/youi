package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Scale9
import io.youi.component.support.PositionSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.image.Image
import spice.net._

class Scale9Example extends UIExampleScreen {
  override def title: String = "Scale 9"
  override def path: URLPath = path"/examples/scale9.html"

  override def createUI(): Task[Unit] = Image("/images/scale9.png").flatMap { image =>
    val x1 = 50.0
    val x2 = 450.0
    val y1 = 50.0
    val y2 = 450.0

    val medium = new Scale9(image, x1, x2, y1, y2) with PositionSupport {
      size.width  @= 400.0
      size.height @= 400.0
      position.center := container.size.center
      position.middle := container.size.middle
    }

    val small = new Scale9(image, x1, x2, y1, y2) with PositionSupport {
      size.width @= 250.0
      size.height @= 250.0
      position.right := medium.position.left - 100.0
      position.middle := container.size.middle
    }

    val large = new Scale9(image, x1, x2, y1, y2) with PositionSupport {
      size.width  @= 600.0
      size.height @= 600.0
      position.left := medium.position.right + 100.0
      position.middle := container.size.middle
    }

    container.children ++= Seq(small, medium, large)

    Task.parSequence(List(small.init(), medium.init(), large.init())).map(_ => ())
  }
}
