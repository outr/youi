package io.youi.material

import io.youi.component.types.Prop
import io.youi.{Color, History, dom}
import io.youi.net._

import scala.concurrent.Future
import scribe.Execution.global

object MaterialComponents {
  lazy val loaded: Future[Unit] = {
    dom.addCSS(History.url.withPath(path"/material-components-web.min.css"))
    dom.addScript(History.url.withPath(path"/material-components-web.min.js")).flatMap { _ =>
      Material.load().map(_ => ())
    }
  }

  def verified[Return](f: => Return): Return = {
    assert(loaded.isCompleted, "MaterialComponents must be loaded before any MDC components can be used!")
    f
  }

  // dom.setCSSVariable("mdc-theme-primary", ClientExampleApplication.colors.blue.dark.toHex)
  object theme {
    lazy val primary: Prop[Color] = new Prop[Color](
      getter = Color.unapply(dom.getCSSVariable("mdc-theme-primary")).getOrElse(Color.Clear),
      setter = c => dom.setCSSVariable("mdc-theme-primary", c.toRGBA)
    )
  }
}