package io.youi.app.screen

import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait LoadingTransitionSupport extends ScreenManager {
  protected def loadingElement: html.Element

  override protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = Future {
    loadingElement.classList.remove("hidden")
  }

  override protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = Future {
    loadingElement.classList.add("hidden")
  }
}
