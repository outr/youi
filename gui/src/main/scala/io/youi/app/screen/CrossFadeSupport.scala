package io.youi.app.screen

import cats.effect.{Deferred, IO}
import io.youi.AnimationFrame
import io.youi.easing.Easing
import io.youi.task._
import org.scalajs.dom.html

import scala.concurrent.duration._

trait CrossFadeSupport extends ScreenManager {
  protected def crossFadeElement: html.Element
  protected def crossFadeDuration: FiniteDuration
  protected def crossFadeEaseIn: Easing = Easing.linear
  protected def crossFadeEaseOut: Easing = Easing.linear

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = {
    if (crossFadeElement.style.visibility != "visible") {
      val workflow = synchronous {
        crossFadeElement.style.visibility = "visible"
        crossFadeElement.style.opacity = "0.0"
      }
        .andThen(AnimateIn(
          get = () => crossFadeElement.style.opacity.toDouble,
          apply = (d: Double) => crossFadeElement.style.opacity = d.toString,
          destination = () => 1.0,
          duration = () => crossFadeDuration,
          easing = crossFadeEaseIn
        ))
      Deferred[IO, Double].flatMap { deferred =>
        workflow.start(AnimationFrame, Some(deferred))
        deferred.get.map(_ => ())
      }
    } else {
      super.beforeScreenChange(oldScreen, newScreen)
    }
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = {
    if (crossFadeElement.style.visibility == "visible") {
      val animation = AnimateIn(
        get = () => crossFadeElement.style.opacity.toDouble,
        apply = (d: Double) => crossFadeElement.style.opacity = d.toString,
        destination = () => 0.0,
        duration = () => crossFadeDuration,
        easing = crossFadeEaseOut
      )
        .andThen(synchronous(crossFadeElement.style.visibility = "hidden"))
      Deferred[IO, Double].flatMap { deferred =>
        animation.start(AnimationFrame, Some(deferred))
        deferred.get.map(_ => ())
      }
    } else {
      super.afterScreenChange(oldScreen, newScreen)
    }
  }
}
