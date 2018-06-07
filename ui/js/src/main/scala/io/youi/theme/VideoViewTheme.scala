package io.youi.theme

import io.youi.component.extras.HTMLComponent
import io.youi.video.Video
import reactify.Var

trait VideoViewTheme extends HTMLComponentTheme {
  lazy val video: StyleProp[Video] = style[Video]("video", Video.empty, Some(VideoViewTheme))
  lazy val autoPauseOnHide: StyleProp[Boolean] = style[Boolean]("autoPauseOnHide", true, None)
}

object VideoViewTheme extends StyleConnect[Video] {
  override def init(theme: Theme, v: StyleProp[Video], name: String): Unit = theme match {
    case c: HTMLComponent[_] => {
      val e = HTMLComponent.element(c)
      v.attachAndFire { video =>
        while (e.hasChildNodes()) {
          e.removeChild(e.firstChild)
        }
        video match {
          case Video.empty => // Nothing to add
          case _ => e.appendChild(video.element)
        }
      }
      // TODO: consider ramifications of the same video in multiple locations
    }
  }
}