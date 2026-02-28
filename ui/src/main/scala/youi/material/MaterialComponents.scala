package youi.material

import rapid.Task
import youi.component.types.Prop
import youi.{Color, dom}
import youi._
import spice.net._

import scala.concurrent.duration._
import scala.scalajs.js
import scala.util.Try

object MaterialComponents {
  private var _loaded = false
  def loaded: Boolean = _loaded

  def waitForLoaded(): Task[Unit] = if (loaded) {
    Task.unit
  } else {
    Task.sleep(100.millis).flatMap(_ => waitForLoaded())
  }

  private val mdcDefined = Try(js.Dynamic.global.mdc).isSuccess
  if (mdcDefined) {
    Material.load().map(_ => _loaded = true).startUnit()
  } else {
    dom.addCSS(url"https://unpkg.com/material-components-web@14.0.0/dist/material-components-web.min.css")
    dom.addScript(url"https://unpkg.com/material-components-web@14.0.0/dist/material-components-web.min.js").flatMap { _ =>
      Material.load().map(_ => _loaded = true)
    }.startUnit()
  }

  def verified[Return](f: => Return): Return = {
    assert(loaded, "MaterialComponents must be loaded before any MDC components can be used!")
    f
  }

  object theme {
    lazy val primary: Prop[Color] = new Prop[Color](
      getter = Color.unapply(dom.getCSSVariable("mdc-theme-primary")).getOrElse(Color.Clear),
      setter = c => dom.setCSSVariable("mdc-theme-primary", c.toRGBA)
    )
  }
}
