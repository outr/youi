package io.youi.app.screen

import cats.effect.IO
import org.scalajs.dom.html
import org.scalajs.dom.html.Element

/**
  * Preloads Screen element from Scala.js usually through Template
  */
trait PreloadedContentScreen extends ContentScreen {
  protected def preloadScreen: html.Element

  override protected def generateScreen(): IO[Element] = {
    IO.pure(preloadScreen)
  }
}
