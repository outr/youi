package io.youi.http.content

import java.io.File

import io.youi.http.Headers
import io.youi.net.ContentType

case class FormDataContent(data: List[FormData]) extends Content {
  override def length: Long = -1
  override def lastModified: Long = -1
  override def contentType: ContentType = ContentType.`multipart/form-data`
  override def withContentType(contentType: ContentType): Content = this
  override def withLastModified(lastModified: Long): Content = this

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

  override def asString: String = toString
}