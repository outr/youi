package io.youi.http

import java.io.File
import java.net.{HttpURLConnection, URL}

import io.youi.net.ContentType
import sun.net.www.protocol.file.FileURLConnection

sealed trait Content {
  def length: Long
  def lastModified: Long
  def contentType: ContentType
}

case class StringContent(value: String, contentType: ContentType, lastModified: Long = System.currentTimeMillis()) extends Content {
  override def length: Long = value.length
}

case class FileContent(file: File, contentType: ContentType) extends Content {
  override def length: Long = file.length()

  override def lastModified: Long = file.lastModified()
}

case class URLContent(url: URL, contentType: ContentType) extends Content {
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
    }
  }

  override def length: Long = contentLength

  override def lastModified: Long = contentModified
}

object Content {
  val empty: Content = string("", ContentType.`text/plain`)
  def string(value: String, contentType: ContentType): Content = StringContent(value, contentType)
  def file(file: File): Content = FileContent(file, ContentType.byFileName(file.getName))
  def file(file: File, contentType: ContentType): Content = FileContent(file, contentType)
  def url(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  def url(url: URL, contentType: ContentType): Content = URLContent(url, contentType)
  def classPath(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  def classPath(path: String): Content = URLContent(Thread.currentThread().getContextClassLoader.getResource(path), ContentType.byFileName(path))
  def classPath(path: String, contentType: ContentType): Content = URLContent(Thread.currentThread().getContextClassLoader.getResource(path), contentType)
}