package io.youi.net

case class URLParseFailure(message: String, cause: Option[Throwable] = None)