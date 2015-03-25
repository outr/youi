package org.hyperscala.screen

import com.outr.net.URL
import org.powerscala.property.Property

import scala.util.matching.Regex

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScreenHandler[S <: Screen](val validator: ScreenValidator[S], screens: Screens)(implicit manifest: Manifest[S]) {
  private val _screen = Property[S](default = None)
  def screen = _screen.readOnlyView

  /**
   * Returns an instance of the enclosed Screen. If no instance is loaded yet, it will be created before returning.
   *
   * @return S
   */
  def apply() = {
    if (screen.get.isEmpty) {
      _screen := validator.load()
    }
    screen()
  }

  def handle(path: String): Boolean = handle(screens.url().copy(path = path))

  def handle(url: URL): Boolean = validator.validate(url, screen.get) match {
    case UseScreen(screen) => if (screen != this.screen()) {      // Different screen supplied
      this.screen.get match {
        case Some(s) => {
          if (isActive) {
            s.deactivate()
          }
          s.dispose()
        }
        case None => // No previous screen
      }
      _screen := screen
      screens._screen := screen
      screens.activate(url, replace = false)
      true
    } else if (!isActive) {                                      // Existing screen, but it's not currently active
      screens._screen := this.screen()
      screens.activate(url, replace = false)
      true
    } else {                                                     // Existing screen and already active, call activate again
      screen.activate(alreadyActive = true)
      true
    }
    case RedirectURL(redirectURL, replace) => {                  // Matched this screen, but redirecting to another URL
      screens.activate(redirectURL, replace)
      true
    }
    case validator.noMatch => false
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
        _screen := validator.load()
        handle(screens.url())
      }
    }
    case None => // Not loaded, nothing to do
  }

  def reLoad() = {
    dispose()
    if (screen.get.isEmpty) {
      _screen := validator.load()
    }
  }
}