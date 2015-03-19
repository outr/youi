package org.hyperscala.screen

import com.outr.net.URL
import org.hyperscala.event.BrowserEvent
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.javascript.dsl._
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.realtime.Realtime
import org.hyperscala.web._
import org.powerscala.Version
import org.hyperscala.html._
import org.powerscala.event.Intercept
import org.powerscala.hierarchy.{AbstractMutableContainer, Element}
import org.powerscala.json.TypedSupport
import org.powerscala.log.Logging
import org.powerscala.property.Property

/**
 * Screens can be mixed into a Webpage to support multiple screens that offer a dynamic alternative to individual page
 * loads.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class Screens private() extends Logging with AbstractMutableContainer[ScreenHandler[_ <: Screen]] {
  implicit def childManifest: Manifest[ScreenHandler[_ <: Screen]] = Screens.screenHandlerManifest

  private var entry: Either[Webpage, HTMLTag] = _

  def this(webpage: Webpage) = {
    this()
    entry = Left(webpage)
    init()
  }

  def this(tag: HTMLTag) = {
    this()
    entry = Right(tag)
    init()
  }

  private def init() = {
    entry match {
      case Left(webpage) => webpage.require(Screens)
      case Right(tag) => tag.require(Screens)
    }

    element.connected[Website] {
      case website => if (url() == null) url := website.request.url.decoded
    }

    withWebpage {
      case webpage => {
        webpage.body.handle[URLChange]({
          case change => if (url != null) {
            url := URL.encoded(change.url).decoded
          }
        }, intercept = Intercept.Continue)

        url.change.on {
          case evt => contents.toStream.map(handler => handler.handle(evt.newValue)).filter(b => b)
        }
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
  }

  def handlerFor(url: URL) = contents.find(handler => handler.validator.matches(url))

  private def withWebpage(f: Webpage => Unit) = entry match {
    case Left(webpage) => f(webpage)
    case Right(tag) => tag.connected[Webpage] {
      case webpage => f(webpage)
    }
  }
  private def element = entry match {
    case Left(webpage) => webpage
    case Right(tag) => tag
  }

  def url = (entry match {
    case Left(webpage) => Some(webpage.store.getOrSet("screenURL", Property[URL]()))
    case Right(tag) => tag.root[Webpage].map(webpage => webpage.store.getOrSet("screenURL", Property[URL]()))
  }).orNull
  private[screen] val _screen = Property[Screen]()
  def screen = _screen.readOnlyView

  childAdded.on {
    case evt => {
      if (url != null && url() != null) {
        evt.child.asInstanceOf[ScreenHandler[Screen]].handle(url())
      }
    }
  }

  def screen[S <: Screen](validator: ScreenValidator[S])(implicit manifest: Manifest[S]): ScreenHandler[S] = {
    val handler = new ScreenHandler[S](validator, this)(manifest)
    this += handler
    handler
  }

  def screen[S <: Screen](matcher: URL => Boolean, loader: => S)(implicit manifest: Manifest[S]): ScreenHandler[S] = {
    val validator = ScreenValidatorBuilder[S](matchers = List(matcher)).load(loader)
    screen(validator)(manifest)
  }

  def +=[S <: Screen](handler: ScreenHandler[S]) = addChild(handler)

  def -=[S <: Screen](handler: ScreenHandler[S]) = removeChild(handler)

  def activate(path: String, replace: Boolean): Unit = activate(url().copy(path = path), replace)

  def activate(url: URL, replace: Boolean): Unit = if (this.url().toString().toLowerCase != url.toString().toLowerCase) {
    withWebpage {
      case webpage => webpage.eval(s"Screen.activate('$url', $replace);")
    }
  }
}

object Screens extends Module {
  private val screenHandlerManifest = implicitly[Manifest[ScreenHandler[_ <: Screen]]]

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