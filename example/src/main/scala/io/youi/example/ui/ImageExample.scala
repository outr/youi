package io.youi.example.ui

import cats.effect.IO
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport, SizeSupport}
import io.youi.component.types.PositionType
import io.youi.example.screen.UIExampleScreen
import io.youi.image.Image
import spice.net._
import io.youi.task._
import io.youi.ui
import reactify._

import scala.concurrent.duration._

class ImageExample extends UIExampleScreen {
  override def title: String = "Image Example"
  override def path: URLPath = path"/examples/image.html"

  override def createUI(): IO[Unit] = Image("/images/icon.png").map { img =>
    container.children += Container(
      new ImageView with PositionSupport with SizeSupport {                       // Top-Left
        image @= img
        position.`type` @= PositionType.Absolute
        position.left @= 50.0
        position.top @= header.size.height + 50.0
        size.width @= 100.0
        size.height @= 100.0
      },
      new ImageView with PositionSupport with MeasuredSupport {                       // Top-Right
        image @= img
        position.`type` @= PositionType.Absolute
        position.right := container.size.width - 50.0
        position.top @= header.size.height + 50.0
        opacity @= 0.5
      },
      new ImageView with PositionSupport with MeasuredSupport {                       // Bottom-Left
        image @= img
        position.`type` @= PositionType.Absolute
        position.left @= 50.0
        position.bottom := ui.size.height - 50.0
      },
      new ImageView with PositionSupport with SizeSupport {                       // Bottom-Right
        image @= img
        position.`type` @= PositionType.Absolute
        position.right := ui.size.width - 50.0
        position.bottom := ui.size.height - 50.0
        size.width @= 300.0
        size.height @= 300.0
      },
      new ImageView with PositionSupport with MeasuredSupport {                       // Center
        image @= img
        position.`type` @= PositionType.Absolute
        position.center := container.size.center
        position.middle := container.size.middle + header.size.height
          forever {
          rotation to 1.0 in 1.seconds andThen(IO(rotation @= 0.0))
        }.start(ImageExample.this)
      }
    )
  }
}