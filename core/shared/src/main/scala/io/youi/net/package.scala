package io.youi

import scala.language.experimental.macros

package object net {
  implicit class URLInterpolator(val sc: StringContext) extends AnyVal {
    def url(args: Any*): URL = macro URL.interpolate
  }

  implicit class IPInterpolator(val sc: StringContext) extends AnyVal {
    def ip(args: Any*): IP = macro IP.interpolate
  }

  implicit class PathInterpolation(val sc: StringContext) extends AnyVal {
    def path(args: Any*): Path = macro Path.interpolate
  }
}