package youi.app.screen

import org.scalajs.dom.html
import org.scalajs.dom.html.Element

import scala.concurrent.Future

/**
  * Preloads Screen element from Scala.js usually through Template
  */
trait PreloadedContentScreen extends ContentScreen {
  protected def preloadScreen: html.Element

  override protected def generateScreen(): Future[Element] = {
    Future.successful(preloadScreen)
  }
}
