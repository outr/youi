package io.youi.http

import java.io.File
import java.net.{HttpURLConnection, JarURLConnection, URL}

import io.youi.net.ContentType
import sun.net.www.protocol.file.FileURLConnection

sealed trait Content {
  def length: Long
  def lastModified: Long
  def contentType: ContentType
}

case class StringContent(value: String, contentType: ContentType, lastModified: Long = System.currentTimeMillis()) extends Content {
  override def length: Long = value.getBytes("UTF-8").length

  override def toString: String = s"StringContent(${value.take(100)}, contentType: $contentType)"
}

case class FileContent(file: File, contentType: ContentType) extends Content {
  override def length: Long = file.length()

  override def lastModified: Long = file.lastModified()

  override def toString: String = s"FileContent(file: ${file.getAbsolutePath}, contentType: $contentType)"
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
      case c: JarURLConnection => {
        c.getContentLengthLong -> c.getLastModified
      }
    }
  }

  override def length: Long = contentLength

  override def lastModified: Long = contentModified

  override def toString: String = s"URLContent(url: $url, contentType: $contentType)"
}

sealed trait RequestContent

case class FormDataContent(data: List[FormData]) extends RequestContent {
  def fileOption(key: String): Option[FileEntry] = data.find(_.key == key).map(_.entries.head.asInstanceOf[FileEntry])
  def stringOption(key: String): Option[StringEntry] = data.find(_.key == key).map(_.entries.head.asInstanceOf[StringEntry])
  def file(key: String): FileEntry = fileOption(key).getOrElse(throw new RuntimeException(s"Not found: $key"))
  def string(key: String): StringEntry = stringOption(key).getOrElse(throw new RuntimeException(s"Not found: $key"))
}

case class FormData(key: String, entries: List[FormDataEntry])

sealed trait FormDataEntry {
  def headers: Headers
}

case class StringEntry(value: String, headers: Headers) extends FormDataEntry

case class FileEntry(fileName: String, file: File, headers: Headers) extends FormDataEntry

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