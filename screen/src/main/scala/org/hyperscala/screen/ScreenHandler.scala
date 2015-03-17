package org.hyperscala.screen

import com.outr.net.URL
import org.powerscala.property.Property

import scala.util.matching.Regex

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScreenHandler[S <: Screen](val matcher: URL => Boolean, preLoad: Boolean, replace: Boolean, screens: Screens, loader: => S, verify: URL => Boolean = ScreenHandler.DefaultVerify)(implicit manifest: Manifest[S]) {
  private val _screen = Property[S](default = None)
  if (preLoad) {
    _screen := loader
  }
  def screen = _screen.readOnlyView

  def activate(uri: String): Unit = if (!isActive && verify(screens.url())) {
    if (!isLoaded) {
      _screen := loader
    }
    screens._screen := screen()
    screens.activate(uri, replace)
  }

  def isLoaded = screen.get.nonEmpty

  def isActive = screens.screen() == screen() && isLoaded

  def dispose() = screen.get match {
    case Some(s) => {
      val active = isActive
      if (active) {
        screens._screen := null
      }
      s.dispose()
      _screen := null.asInstanceOf[S]
      if (active) {
        _screen := loader
        activate(screens.url().toString())
      }
    }
    case None => // Not loaded, nothing to do
  }

  def reLoad() = {
    dispose()
    if (screen.get.isEmpty) {
      _screen := loader
    }
  }
}

object ScreenHandler {
  val DefaultVerify = (url: URL) => true

  def pathMatcher(path: String) = (url: URL) => url.path.equalsIgnoreCase(path)
  def pathsMatcher(paths: List[String]) = (url: URL) => paths.find(uri => url.path.equalsIgnoreCase(uri)).nonEmpty
  def regexMatcher(regex: Regex) = (url: URL) => regex.pattern.matcher(url.toString()).matches()
}