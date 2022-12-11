package io.youi.app.screen

import cats.effect.IO
import org.scalajs.dom.html

trait LoadingTransitionSupport extends ScreenManager {
  protected def loadingElement: html.Element

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = IO {
    loadingElement.classList.remove("hidden")
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = IO {
    loadingElement.classList.add("hidden")
  }
}
