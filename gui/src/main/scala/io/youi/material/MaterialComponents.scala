package io.youi.material

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import io.youi.component.types.Prop
import io.youi.{Color, History, dom}
import spice.net._

import scala.scalajs.js
import scala.util.Try

object MaterialComponents {
  private var _loaded = false
  def loaded: Boolean = _loaded

  private val mdcDefined = Try(js.Dynamic.global.mdc).isSuccess
  if (mdcDefined) {
    Material.load().map(_ => _loaded = true).unsafeRunAndForget()
  } else {
    dom.addCSS(History.url.withPath(path"/material-components-web.min.css"))
    dom.addScript(History.url.withPath(path"/material-components-web.min.js")).flatMap { _ =>
      Material.load().map(_ => _loaded = true)
    }.unsafeRunAndForget()
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