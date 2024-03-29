package io.youi.http.content

import java.io.File

import io.youi.net.ContentType
import io.youi.stream.Stream

case class FileContent(file: File, contentType: ContentType, lastModifiedOverride: Option[Long] = None) extends Content {
  assert(file.isFile, s"Cannot send back ${file.getAbsolutePath} as it is a directory or does not exist!")

  override def length: Long = file.length()

  override def withContentType(contentType: ContentType): Content = copy(contentType = contentType)
  override def withLastModified(lastModified: Long): Content = copy(lastModifiedOverride = Some(lastModified))

  override def lastModified: Long = lastModifiedOverride.getOrElse(file.lastModified())

  override def toString: String = s"FileContent(file: ${file.getAbsolutePath}, contentType: $contentType)"

  override def asString: String = Stream.apply(file, new StringBuilder).toString
}