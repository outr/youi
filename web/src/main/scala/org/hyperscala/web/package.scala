package org.hyperscala

import org.hyperscala.web.{Webpage, VisualException}

import language.implicitConversions
import org.hyperscala.html.HTMLTag
import org.hyperscala.module.{Interface, Module}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object web {
  implicit def tag2WebpageTag(t: HTMLTag) = new WebpageTag(t)

  implicit def throwable2Div(t: Throwable) = new VisualExceptionCreator(t)
}

class VisualExceptionCreator(t: Throwable) {
  def toHTML = new VisualException(t)
}

class WebpageTag(t: HTMLTag) {
  def require(module: Module) = t.connected[Webpage[_]] {
    case webpage => webpage.require(module)
  }

  def require(name: String) = t.connected[Webpage[_]] {
    case webpage => webpage.require(name)
  }

  def require(interface: Interface) = t.connected[Webpage[_]] {
    case webpage => webpage.require(interface)
  }

  def require(interface: Interface, default: Module) = t.connected[Webpage[_]] {
    case webpage => webpage.require(interface, default)
  }

  def webpage[S <: Session](implicit manifest: Manifest[S]) = t.root[Webpage[S]].getOrElse(throw new RuntimeException("No Webpage in the ancestor hierarchy!"))
  def website[S <: Session](implicit manifest: Manifest[S]) = webpage[S].website
  def request[S <: Session](implicit manifest: Manifest[S]) = website[S].request
  def session[S <: Session](implicit manifest: Manifest[S]) = website[S].session
}