package org.hyperscala.web

import com.outr.net.http.{HttpApplication, WebApplication}
import org.powerscala.MapStorage
import com.outr.net.http.session.Session
import org.powerscala.reflect._
import com.outr.net.http.handler.CachedHandler
import org.powerscala.log.Logging

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
    handlers += CachedHandler     // Add caching support
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

    try {
      pages.foreach {
        case page => page.update(delta)
      }
    } catch {
      case t: Throwable => error("Exception thrown while updating pages.", t)
    }
  }
}

object Website {
  def apply() = HttpApplication().asInstanceOf[Website[Session]]
}

class Pages[S <: Session](website: Website[S]) extends Iterable[Webpage] with Logging {
  def ids = website._pages.map.keys

  def byId[W <: Webpage](pageId: String) = website._pages.get[W](pageId)

  // TODO: is there a better way to avoid updating duplicates of the same page?
  def iterator = website._pages.values.toSet[Webpage].iterator

  def remove(page: Webpage) = synchronized {
    debug(s"Removing webpage: ${page.pageId} (${page.getClass.getSimpleName})")
    website._pages.map.foreach {
      case (id, p) if p eq page => website._pages.remove(id)      // Clear all references of the page
      case _ => // Ignore others
    }
  }

  def apply[W <: Webpage](implicit manifest: Manifest[W]) = iterator.collect {
    case w if w.getClass.hasType(manifest.runtimeClass) => w.asInstanceOf[W]
  }
}