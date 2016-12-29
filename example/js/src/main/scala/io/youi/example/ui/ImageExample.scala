package io.youi.example.ui

import com.outr.props._
import com.outr.scribe.Logging
import io.youi.UI
import io.youi.html.style.Image
import io.youi.html.{Button, ImageView}
import io.youi.net.URL

import scala.scalajs.js.annotation.JSExportTopLevel

object ImageExample extends Logging {
  import UI._

  title := "Image Example"

  children += new ImageView {
    image := Image(URL("https://img.buzzfeed.com/buzzfeed-static/static/2014-07/28/10/enhanced/webdr11/enhanced-12094-1406557546-35.jpg"))
    position.center := UI.position.center
    position.middle := UI.position.middle
  }

  @JSExportTopLevel("imageExample")
  def main(): Unit = {
    init()
  }
}
