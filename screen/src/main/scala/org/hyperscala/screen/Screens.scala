package org.hyperscala.screen

import com.outr.net.URL
import com.outr.net.http.session.Session
import org.hyperscala.event.BrowserEvent
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.html._
import org.powerscala.json.TypedSupport
import org.powerscala.log.Logging
import org.powerscala.property.Property

/**
 * Screens can be mixed into a Webpage to support multiple screens that offer a dynamic alternative to individual page
 * loads.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class Screens(webpage: Webpage[_ <: Session]) extends Logging {
  webpage.require(Screens)

  val url = Property[URL](default = Some(webpage.website.request.url.decoded))

  private var loaders = Map.empty[String, ScreenLoader]
  private[screen] var screens = Map.empty[Screen, String]

  webpage.body.handle[URLChange] {
    case change => url := URL(change.url).decoded
  }
  url.change.on {
    case evt => loaders.get(evt.newValue.path) match {
      case Some(loader) => loader.activate()
      case None => warn(s"No screen associated with ${evt.newValue}.")
    }
  }

  def addScreen(uri: String, screen: => Screen, lazyLoad: Boolean = true): Unit = {
    val loader = new ScreenLoader(uri, this, screen)
    if (!lazyLoad) {
      loader.screen   // Load immediately
    }
    synchronized {
      loaders += uri -> loader
    }
    if (uri == url().path) {
      loader.activate()
    }
  }

  def activate(uri: String, replace: Boolean): Unit = {
    webpage.eval(JavaScriptString(s"Screen.activate('$uri', $replace);"))
  }

  def activate(screen: Screen, replace: Boolean): Unit = {
    activate(screens(screen), replace)
  }
}

class ScreenLoader(val uri: String, screens: Screens, loader: => Screen) {
  lazy val screen: Screen = {
    val s: Screen = loader
    screens.synchronized {
      screens.screens += s -> uri
    }
    s
  }

  def activate() = screen.activate()
  def deactivate() = screen.deactivate()
}

object Screens extends Module {
  TypedSupport.register("urlChange", classOf[URLChange])

  val name = "screens"
  val version = Version(1, 0, 0)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/hyperscala-screen.js", "hyperscala-screen.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-screen.js")
  }
}

case class URLChange(tag: HTMLTag, url: String) extends BrowserEvent