package io.youi.http.content

import java.net.{HttpURLConnection, JarURLConnection, URL}

import io.youi.net.ContentType
import io.youi.stream.Stream
import sun.net.www.protocol.file.FileURLConnection

case class URLContent(url: URL, contentType: ContentType, lastModifiedOverride: Option[Long] = None) extends Content {
  assert(url != null, "URL must not be null.")

  override def withContentType(contentType: ContentType): Content = copy(contentType = contentType)
  override def withLastModified(lastModified: Long): Content = copy(lastModifiedOverride = Some(lastModified))

  private lazy val (contentLength, contentModified) = {
    val connection = url.openConnection()
    connection match {
      case c: HttpURLConnection => try {
        c.setRequestMethod("HEAD")
        c.getInputStream
        c.getContentLengthLong -> c.getLastModified
      } finally {
        c.disconnect()
      }
      case c: FileURLConnection => {
        c.getContentLengthLong -> c.getLastModified
      }
      case c: JarURLConnection => {
        c.getContentLengthLong -> c.getLastModified
      }
    }
  }

  override def length: Long = contentLength

  override def lastModified: Long = lastModifiedOverride.getOrElse(contentModified)

  override def toString: String = s"URLContent(url: $url, contentType: $contentType)"

  override def asString: String = Stream.apply(url, new StringBuilder).toString
}