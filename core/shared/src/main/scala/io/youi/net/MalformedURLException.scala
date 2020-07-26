package io.youi.net

case class MalformedURLException(message: String, url: String, cause: Option[Throwable]) extends RuntimeException(message, cause.orNull)