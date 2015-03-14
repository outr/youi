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
class Screens(webpage: Webpage) extends Logging {
  webpage.require(Screens)

  val url = Property[URL](default = Some(webpage.website.request.url.decoded))
  private[screen] val _screen = Property[Screen]()
  def screen = _screen.readOnlyView

  private var keeperURIs = Map.empty[String, ScreenKeeper[_ <: Screen]]

  webpage.body.handle[URLChange] {
    case change => url := URL.encoded(change.url).decoded
  }
  url.change.on {
    case evt => keeperURIs.get(evt.newValue.path) match {
      case Some(keeper) => keeper.activate()
      case None => warn(s"No screen associated with ${evt.newValue}.")
    }
  }
  screen.change.on {
    case evt => {
      if (evt.oldValue != null) {
        evt.oldValue.deactivate()
      }
      if (evt.newValue != null) {
        evt.newValue.activate()
      }
    }
  }

  def screen[S <: Screen](uri: String, loader: => S, preLoad: Boolean = false, replace: Boolean = false)(implicit manifest: Manifest[S]) = {
    val keeper = new ScreenKeeper[S](uri, preLoad, replace, this, loader)(manifest)
    synchronized {
      keeperURIs += uri -> keeper
    }
    if (uri == url().path) {          // This screen matches the current URL, load immediately!
      keeper.activate()
    }
    keeper
  }

  def activate(uri: String, replace: Boolean): Unit = if (!url().toString().toLowerCase.endsWith(uri.toLowerCase)) {
    webpage.eval(JavaScriptString(s"Screen.activate('$uri', $replace);"))
  }
}

object Screens extends Module {
  TypedSupport.register("urlChange", classOf[URLChange])

  val name = "screens"
  val version = Version(1, 0, 0)

  override def dependencies = List(Realtime)

  override def init(website: Website) = {
    website.register("/js/hyperscala-screen.js", "hyperscala-screen.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-screen.js")
  }
}

case class URLChange(tag: HTMLTag, url: String) extends BrowserEvent