package io.youi.server

import io.youi.net.URL

class ServerException(val message: String,
                      cause: Throwable,
                      val url: URL) extends RuntimeException(s"$message ($url)", cause)