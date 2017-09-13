package io.youi

import scala.language.experimental.macros

package object net {
  implicit class URLInterpolator(val sc: StringContext) extends AnyVal {
    def url(args: Any*): URL = macro URL.interpolate
  }
}