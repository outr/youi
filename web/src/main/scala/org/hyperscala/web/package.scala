package org.hyperscala

import org.hyperscala.web.{Webpage, VisualException}

import language.implicitConversions
import org.hyperscala.html.HTMLTag
import org.hyperscala.module.Module

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
  def require(module: Module) = t.connected[Webpage] {
    case webpage => webpage.require(module)
  }

  def webpageRequest = t.root[Webpage].getOrElse(throw new RuntimeException("No Webpage in the ancestor hierarchy!")).webpageRequest
  def webpageSession = t.root[Webpage].getOrElse(throw new RuntimeException("No Webpage in the ancestor hierarchy!")).webpageSession
}