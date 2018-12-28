package io.youi.http.content

import java.io.File
import java.net.URL

import io.youi.net.ContentType

import scala.language.implicitConversions
import scala.xml.{Elem, PrettyPrinter}

trait ContentHelpers extends SharedContentHelpers {
  private lazy val xmlPrinter = new PrettyPrinter(80, 4)

  implicit def file2Content(file: File): Content = this.file(file)
  implicit def url2Content(url: java.net.URL): Content = this.url(url)

  override def xml(value: Elem, contentType: ContentType): Content = StringContent(xmlPrinter.format(value), contentType)
  override def file(file: File): Content = FileContent(file, ContentType.byFileName(file.getName))
  override def file(file: File, contentType: ContentType): Content = FileContent(file, contentType)
  override def url(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  override def url(url: URL, contentType: ContentType): Content = URLContent(url, contentType)
  override def classPath(url: URL): Content = URLContent(url, ContentType.byFileName(url.toString))
  override def classPath(path: String, contentType: ContentType): Content = URLContent(Thread.currentThread().getContextClassLoader.getResource(path), contentType)
}