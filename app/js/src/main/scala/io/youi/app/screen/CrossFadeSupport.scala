package io.youi.app.screen

import io.youi.easing.Easing
import io.youi.workflow.{AnimateIn, synchronous}
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.duration._

trait CrossFadeSupport extends ScreenManager {
  protected def crossFadeElement: html.Element
  protected def crossFadeDuration: FiniteDuration
  protected def crossFadeEaseIn: Easing = Easing.linear
  protected def crossFadeEaseOut: Easing = Easing.linear

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    if (crossFadeElement.style.visibility != "visible") {
      val workflow =  synchronous {
        crossFadeElement.style.visibility = "visible"
        crossFadeElement.style.opacity = "0.0"
      }
        .andThen(AnimateIn(
          get = () => crossFadeElement.style.opacity.toDouble,
          apply = (d: Double) => crossFadeElement.style.opacity = d.toString,
          destination = () => 1.0,
          duration = crossFadeDuration,
          easing = crossFadeEaseIn
        ))
      workflow.start()
    } else {
      super.beforeScreenChange(oldScreen, newScreen)
    }
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    if (crossFadeElement.style.visibility == "visible") {
      AnimateIn(
        get = () => crossFadeElement.style.opacity.toDouble,
        apply = (d: Double) => crossFadeElement.style.opacity = d.toString,
        destination = () => 0.0,
        duration = crossFadeDuration,
        easing = crossFadeEaseOut
      ).andThen(synchronous(crossFadeElement.style.visibility = "hidden")).start()
    } else {
      super.afterScreenChange(oldScreen, newScreen)
    }
  }
}
