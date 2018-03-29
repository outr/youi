package io.youi.http

import java.io.File
import java.net.{HttpURLConnection, JarURLConnection, URL}

import io.youi.net.ContentType
import sun.net.www.protocol.file.FileURLConnection

import scala.xml.{Elem, PrettyPrinter}

trait Content extends RequestContent {
  def length: Long
  def lastModified: Long
  def contentType: ContentType

  def withContentType(contentType: ContentType): Content
  def withLastModified(lastModified: Long): Content
}

case class StringContent(value: String, contentType: ContentType, lastModified: Long = System.currentTimeMillis()) extends Content {
  override def length: Long = value.getBytes("UTF-8").length

  override def withContentType(contentType: ContentType): Content = copy(contentType = contentType)
  override def withLastModified(lastModified: Long): Content = copy(lastModified = lastModified)

  override def toString: String = s"StringContent(${value.take(100)}, contentType: $contentType)"
}

case class FileContent(file: File, contentType: ContentType, lastModifiedOverride: Option[Long] = None) extends Content {
  assert(file.isFile, s"Cannot send back ${file.getAbsolutePath} as it is a directory or does not exist!")

  override def length: Long = file.length()

  override def withContentType(contentType: ContentType): Content = copy(contentType = contentType)
  override def withLastModified(lastModified: Long): Content = copy(lastModifiedOverride = Some(lastModified))

  override def lastModified: Long = lastModifiedOverride.getOrElse(file.lastModified())

  override def toString: String = s"FileContent(file: ${file.getAbsolutePath}, contentType: $contentType)"
}

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
}

sealed trait RequestContent

case class FormDataContent(data: List[FormData]) extends RequestContent {
  def fileOption(key: String): Option[FileEntry] = data.find(_.key == key).map(_.entries.head.asInstanceOf[FileEntry])
  def stringOption(key: String): Option[StringEntry] = data.find(_.key == key).map(_.entries.head.asInstanceOf[StringEntry])
  def file(key: String): FileEntry = fileOption(key).getOrElse(throw new RuntimeException(s"Not found: $key in $this."))
  def string(key: String): StringEntry = stringOption(key).getOrElse(throw new RuntimeException(s"Not found: $key in $this."))

  def withFile(key: String, fileName: String, file: File, headers: Headers = Headers.empty): FormDataContent = {
    val entry = FileEntry(fileName, file, headers)
    withEntry(key, entry)
  }

  def withString(key: String, value: String, headers: Headers = Headers.empty): FormDataContent = {
    val entry = StringEntry(value, headers)
    withEntry(key, entry)
  }

  def withEntry(key: String, entry: FormDataEntry): FormDataContent = {
    val formData = data.find(_.key == key).getOrElse(FormData(key, Nil))
    val updated = formData.copy(entries = formData.entries ::: List(entry))
    copy(data = data.filterNot(_.key == key) ::: List(updated))
  }

  override def toString: String = s"FormDataContent(${data.map(_.key).mkString(", ")})"
}

case class FormData(key: String, entries: List[FormDataEntry])

sealed trait FormDataEntry {
  def headers: Headers
}

case class StringEntry(value: String, headers: Headers) extends FormDataEntry

case class FileEntry(fileName: String, file: File, headers: Headers) extends FormDataEntry

object Content {
  private lazy val xmlPrinter = new PrettyPrinter(80, 4)

  val empty: Content = string("", ContentType.`text/plain`)
  lazy val form: FormDataContent = FormDataContent(Nil)
  def string(value: String, contentType: ContentType): Content = StringContent(value, contentType)
  def xml(value: Elem, contentType: ContentType): Content = StringContent(xmlPrinter.format(value), contentType)
  def file(file: File): Content = FileContent(file, ContentType.byFileName(file.getName))
  def file(file: File, contentType: ContentType): Content = FileContent(file, contentType)
  def url(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  def url(url: URL, contentType: ContentType): Content = URLContent(url, contentType)
  def classPath(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  def classPath(path: String): Content = classPathOption(path).getOrElse(throw new RuntimeException(s"Invalid URL or not found in class-loader: $path."))
  def classPathOption(path: String): Option[Content] = {
    val o = Option(Thread.currentThread().getContextClassLoader.getResource(path))
    o.map(classPath)
  }
  def classPath(path: String, contentType: ContentType): Content = URLContent(Thread.currentThread().getContextClassLoader.getResource(path), contentType)
}