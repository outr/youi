package io.youi.http

import java.io.File
import java.net.{HttpURLConnection, URL}

sealed trait Content {
  def length: Long
  def lastModified: Long
}

case class StringContent(value: String, lastModified: Long = System.currentTimeMillis()) extends Content {
  override def length: Long = value.length
}

case class FileContent(file: File) extends Content {
  override def length: Long = file.length()

  override def lastModified: Long = file.lastModified()
}

case class URLContent(url: URL) extends Content {
  private lazy val (contentLength, contentModified) = {
    val c = url.openConnection().asInstanceOf[HttpURLConnection]
    try {
      c.setRequestMethod("HEAD")
      c.getInputStream
      c.getContentLengthLong -> c.getLastModified
    } finally {
      c.disconnect()
    }
  }

  override def length: Long = contentLength

  override def lastModified: Long = contentModified
}

object Content {
  def string(value: String): Content = StringContent(value)
  def file(file: File): Content = FileContent(file)
  def url(url: URL): Content = URLContent(url)
  def classPath(path: String): Content = URLContent(Thread.currentThread().getContextClassLoader.getResource(path))
}