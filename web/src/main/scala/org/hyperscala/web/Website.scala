package org.hyperscala.web

import com.outr.net.http.{HttpApplication, WebApplication}
import org.powerscala.MapStorage
import com.outr.net.http.session.Session

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
  val pages = new MapStorage[String, Webpage]

  def init() = {
  }

  def page(creator: => Webpage, scope: Scope, uris: String*)

  override def dispose() = {
    super.dispose()

    application.clear()     // Clear out the application storage at dispose time
  }

  override def update(delta: Double) = {
    super.update(delta)

    pages.map.values.toSet[Webpage].foreach {      // TODO: is there a better way to avoid updating duplicates of the same page?
      case page => page.update(delta)
    }
  }
}

object Website {
  def apply() = HttpApplication().asInstanceOf[Website[Session]]
}