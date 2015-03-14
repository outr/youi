package org.hyperscala.ui

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.event.{Listenable, Intercept}
import org.powerscala.json.TypedSupport
import org.powerscala.property.Property
import com.outr.net.http.session.Session

/**
 * WindowSize maintains the webpage window size when required on a webpage.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object WindowSize extends Module with Listenable {
  TypedSupport.register("windowSize", classOf[WindowSize])

  val name = "windowSize"
  val version = Version(1)

  override def dependencies = List(WindowSized, Realtime)

  /**
   * Should always reflect the width of the webpage window.
   */
  def width(webpage: Webpage) = webpage.store.getOrSet("windowWidth", new Property[Int]())

  /**
   * Should always reflect the height of the webpage window.
   */
  def height(webpage: Webpage) = webpage.store.getOrSet("windowHeight", new Property[Int]())

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    webpage.body.handle[WindowSize] {
      case evt => {
        width(webpage) := evt.width
        height(webpage) := evt.height
      }
    }
    // TODO: modify to send at a maximum frequency
    val js =
      s"""
        |realtime.send({
        | id: '${webpage.body.identity}',
        | type: 'windowSize',
        | width: windowWidth,
        | height: windowHeight
        |});
      """.stripMargin
    WindowSized.resized(webpage, JavaScriptString(js))
  }
}

case class WindowSize(tag: HTMLTag, width: Int, height: Int) extends BrowserEvent