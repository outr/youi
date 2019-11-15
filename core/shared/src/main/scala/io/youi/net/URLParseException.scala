package io.youi.net

class URLParseException(message: String, cause: Option[Throwable]) extends RuntimeException(message, cause.orNull)
