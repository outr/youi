package io.youi.util

import java.net.{URLDecoder, URLEncoder}

object URLUtil {
  def decode(value: String): String = URLDecoder.decode(value.replaceAllLiterally("%20", "+"), "UTF-8")
  def encode(value: String): String = URLEncoder.encode(value, "UTF-8").replaceAllLiterally("+", "%20")
}
