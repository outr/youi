package io.youi.example.ui

import cats.effect.IO
import io.youi._
import io.youi.component.support.{MeasuredSupport, PositionSupport}
import io.youi.component.types.{PositionType, WhiteSpace}
import io.youi.component.{ImageView, TextView}
import io.youi.easing.Easing
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.image.Image
import io.youi.task._
import reactify._
import spice.net._

import scala.concurrent.duration._

class AnimationExample extends UIExampleScreen {
  override def title: String = "Animation Example"
  override def path: URLPath = path"/examples/animation.html"

  override def createUI(): IO[Unit] = {
    scribe.info("Create UI!")
    Image("/images/icon.png").flatMap { img =>
      scribe.info(s"Image loaded! ${img.size}")
      GoogleFont.`Open Sans`.`regular`.load().map { fnt =>
        scribe.info(s"Font loaded! ${fnt.name}")
        var shift = header.size.height()
        Easing.map.toList.sortBy(_._1).foreach {
          case (name, easingFunction) => {
            val label = new TextView with PositionSupport with MeasuredSupport {
              content @= s"$name Example"
              font.size @= 24.0
              font.weight @= fnt
              position.`type` @= PositionType.Absolute
              position.top @= shift
              position.left @= 50.0
              color @= Color.DarkSlateBlue
              whiteSpace @= WhiteSpace.NoWrap

              shift += 25.0
            }

            forever(
              sequential(
                label.position.right to container.size.width - 50.0 in 5.seconds easing easingFunction,
                sleep(2.seconds),
                label.position.left to 50.0 in 5.seconds easing easingFunction,
                sleep(2.seconds)
              )
            ).start(this)

            container.children += label
          }
        }

        val image = new ImageView with PositionSupport with MeasuredSupport {
          image @= img
          position.`type` @= PositionType.Absolute
          position.left @= 0.0
          position.middle := container.size.middle + header.size.height
        }

        container.children += image

        forever(
          parallel(
            sequential(
              synchronous(image.rotation @= 0.0),
              image.rotation to 6.0 in 20.seconds
            ),
            sequential(
              image.position.right to ui.size.width in 5.seconds easing Easing.bounceOut,
              image.position.bottom to ui.size.height in 5.seconds easing Easing.bounceOut,
              image.position.left to 0.0 in 5.seconds easing Easing.bounceOut,
              image.position.top to header.size.height in 5.seconds easing Easing.bounceOut
            )
          )
        ).start(this)
      }
    }
  }
}