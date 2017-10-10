package io.youi.client

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream, OutputStream}

import org.apache.http.HttpEntity

class MultipartEntityWrapper(e: HttpEntity) extends HttpEntity {
  override def getContentEncoding = e.getContentEncoding

  override def getContent: InputStream = {
    val out = new ByteArrayOutputStream
    writeTo(out)
    out.flush()
    new ByteArrayInputStream(out.toByteArray)
  }

  override def getContentLength = e.getContentLength

  override def writeTo(out: OutputStream) = e.writeTo(out)

  override def isRepeatable = e.isRepeatable

  override def isChunked = e.isChunked

  override def isStreaming = e.isStreaming

  override def getContentType = e.getContentType

  override def consumeContent() = {}
}
