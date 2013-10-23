package org.hyperscala.web

import com.outr.net.http.{HttpApplication, WebApplication}
import org.powerscala.MapStorage
import com.outr.net.http.session.Session
import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Website[S <: Session] extends WebApplication[S] {
  /**
   * Application stores content that should be persistent throughout the life of the website.
   */
  val application = new MapStorage[Any, Any]
  /**
   * Keeps a reference to all currently loaded pages referenced by id.
   */
  private[web] val _pages = new MapStorage[String, Webpage]
  /**
   * Access to all currently loaded pages.
   */
  lazy val pages = new Pages[S](this)

  def init() = {
  }

  def page(creator: => Webpage, scope: Scope, uris: String*) = {
    if (uris.isEmpty) {
      throw new RuntimeException("Page must have at least one URI associated!")
    }
    val handler = new WebpageHandler(() => creator, scope, uris.toList)
    addHandler(handler, uris: _*)
    handler
  }

  override def dispose() = {
    super.dispose()

    application.clear()     // Clear out the application storage at dispose time
  }

  override def update(delta: Double) = {
    super.update(delta)

    _pages.map.values.toSet[Webpage].foreach {      // TODO: is there a better way to avoid updating duplicates of the same page?
      case page => page.update(delta)
    }
  }
}

object Website {
  def apply() = HttpApplication().asInstanceOf[Website[Session]]
}

class Pages[S <: Session](website: Website[S]) extends Iterable[Webpage] {
  def iterator = website._pages.values.iterator

  def apply[W <: Webpage](implicit manifest: Manifest[W]) = iterator.collect {
    case w if w.getClass.hasType(manifest.runtimeClass) => w.asInstanceOf[W]
  }
}