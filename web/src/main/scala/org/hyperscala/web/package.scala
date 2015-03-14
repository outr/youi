package org.hyperscala

import org.hyperscala.web.{Webpage, VisualException}

import language.implicitConversions
import org.hyperscala.html._
import org.hyperscala.module.{Interface, Module}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object web {
  implicit class VisualExceptionCreator(t: Throwable) {
    def toHTML = new VisualException(t)
  }

  implicit class WebpageTag(t: HTMLTag) {
    def require(module: Module) = t.connected[Webpage] {
      case webpage => webpage.require(module)
    }

    def require(name: String) = t.connected[Webpage] {
      case webpage => webpage.require(name)
    }

    def require(interface: Interface) = t.connected[Webpage] {
      case webpage => webpage.require(interface)
    }

    def require(interface: Interface, default: Module) = t.connected[Webpage] {
      case webpage => webpage.require(interface, default)
    }

    def webpage = t.root[Webpage].getOrElse(throw new RuntimeException("No Webpage in the ancestor hierarchy!"))
    def website = webpage.website
    def request = website.request
    def session = website.session
  }
}