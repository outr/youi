package io.youi.net

case class MalformedURLException(message: String, url: String) extends RuntimeException(message)