package org.hyperscala.screen

import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScreenKeeper[S <: Screen] private[screen](val uri: String, preLoad: Boolean, replace: Boolean, screens: Screens, loader: => S)(implicit manifest: Manifest[S]) {
  private val _screen = Property[S](default = None)
  if (preLoad) {
    _screen := loader
  }
  def screen = _screen.readOnlyView

  def activate(): Unit = if (!isActive) {
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
        activate()
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