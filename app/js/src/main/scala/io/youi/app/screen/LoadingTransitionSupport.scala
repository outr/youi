package io.youi.app.screen

import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait LoadingTransitionSupport extends ScreenManager {
  protected def loadingElement: html.Element

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = Future {
    loadingElement.classList.remove("hidden")
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = Future {
    loadingElement.classList.add("hidden")
  }
}
