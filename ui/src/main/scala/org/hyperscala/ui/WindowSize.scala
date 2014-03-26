package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.event.{Listenable, Intercept}
import org.powerscala.property.Property
import com.outr.net.http.session.Session

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
  def width[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("windowWidth", new Property[Int]())

  /**
   * Should always reflect the height of the webpage window.
   */
  def height[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("windowHeight", new Property[Int]())

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.body.eventReceived.on {
      case evt => if (evt.event == "window_size") {
        val width = evt.json.int("width")
        val height = evt.json.int("height")

        this.width(webpage) := width
        this.height(webpage) := height

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
    WindowSized.resized(webpage, JavaScriptString(js))
  }
}
