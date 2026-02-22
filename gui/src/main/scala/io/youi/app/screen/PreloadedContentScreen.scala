package io.youi.app.screen

import rapid.Task
import org.scalajs.dom.html
import org.scalajs.dom.html.Element

/**
  * Preloads Screen element from Scala.js usually through Template
  */
trait PreloadedContentScreen extends ContentScreen {
  protected def preloadScreen: html.Element

  override protected def generateScreen(): Task[Element] = {
    Task.pure(preloadScreen)
  }
}
