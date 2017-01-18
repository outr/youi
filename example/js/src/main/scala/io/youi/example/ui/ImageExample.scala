package io.youi.example.ui

import com.outr.reactify._
import com.outr.scribe._
import io.youi.{AnimationFrame, UI}
import io.youi.html.style.Image
import io.youi.html.{Button, ImageView}
import io.youi.net.URL
import org.scalajs.dom.window

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.concurrent.duration._

object ImageExample {
  import UI._

  title := "Image Example"

  import monix.execution.Scheduler.{global => scheduler}

  children += new ImageView {
    image := Image(URL("https://img.buzzfeed.com/buzzfeed-static/static/2014-07/28/10/enhanced/webdr11/enhanced-12094-1406557546-35.jpg"))
    position.center := UI.position.center
    position.middle := UI.position.middle

    rotation := AnimationFrame.timeStamp * 0.0001
  }

  @JSExportTopLevel("imageExample")
  def main(): Unit = {
    init()
  }
}
