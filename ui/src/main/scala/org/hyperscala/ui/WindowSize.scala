package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.Webpage
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.event.{Listenable, Intercept}
import org.powerscala.property.Property

/**
 * WindowSize maintains the webpage window size when required on a webpage.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object WindowSize extends Module with Listenable {
  val name = "windowSize"
  val version = Version(1)

  override def dependencies = List(WindowSized, Realtime)

  /**
   * Should always reflect the width of the webpage window.
   */
  def width = Webpage().store.getOrSet("windowWidth", new Property[Int]())

  /**
   * Should always reflect the height of the webpage window.
   */
  def height = Webpage().store.getOrSet("windowHeight", new Property[Int]())

  def init() = {}

  def load() = {
    val page = Webpage()
    page.body.eventReceived.on {
      case evt => if (evt.event == "window_size") {
        val width = evt.json.int("width")
        val height = evt.json.int("height")

        this.width := width
        this.height := height

        Intercept.Stop
      } else {
        Intercept.Continue
      }
    }
    val js =
      """
        |groupedSend('windowSizeChange', 250, 'window_size', null, {
        | width: windowWidth,
        | height: windowHeight
        |});
      """.stripMargin
    WindowSized.resized(JavaScriptString(js))
  }
}
