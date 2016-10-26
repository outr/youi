package io.youi.util

import scala.scalajs.js.URIUtils

object URLUtil {
  def decode(value: String): String = URIUtils.decodeURIComponent(value)
  def encode(value: String): String = URIUtils.encodeURIComponent(value)
}
