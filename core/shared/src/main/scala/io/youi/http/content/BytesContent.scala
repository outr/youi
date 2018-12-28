package io.youi.http.content

import io.youi.net.ContentType

case class BytesContent(value: Array[Byte], contentType: ContentType, lastModified: Long = System.currentTimeMillis()) extends Content {
  override def length: Long = value.length

  override def withContentType(contentType: ContentType): Content = copy(contentType = contentType)
  override def withLastModified(lastModified: Long): Content = copy(lastModified = lastModified)

  override def toString: String = s"BytesContent(${value.take(100)}, contentType: $contentType)"

  override def asString: String = new String(value, "UTF-8")
}