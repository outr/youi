package io.youi.http

import java.io.OutputStream

import io.youi.net.ContentType

abstract class StreamContent(val contentType: ContentType,
                             val lastModified: Long = System.currentTimeMillis(),
                             val length: Long = -1L) extends Content {
  def stream(out: OutputStream): Unit
}
