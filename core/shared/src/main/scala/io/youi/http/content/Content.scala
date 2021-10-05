package io.youi.http.content

import fabric._
import fabric.parse.Json

import java.io.File
import java.net.URL
import io.youi.net.ContentType

trait Content {
  def length: Long
  def lastModified: Long
  def contentType: ContentType

  def withContentType(contentType: ContentType): Content
  def withLastModified(lastModified: Long): Content
  def asString: String
}

object Content extends SharedContentHelpers with ContentHelpers

trait SharedContentHelpers {
  val empty: Content = string("", ContentType.`text/plain`)
  lazy val form: FormDataContent = FormDataContent(Nil)

  def graphql(query: String, operationName: Option[String] = None, variables: List[(String, Value)] = Nil): Content = {
    json(obj(
      "query" -> str(query),
      "operationName" -> operationName.map(str).getOrElse(Null),
      "variables" -> obj(variables: _*)
    ))
  }
  def json(value: Value, pretty: Boolean = false): Content = {
    string(Json.format(value), ContentType.`application/json`)
  }
  def string(value: String, contentType: ContentType): Content = StringContent(value, contentType)
  def bytes(value: Array[Byte], contentType: ContentType): Content = BytesContent(value, contentType)
  def classPath(path: String): Content = classPathOption(path).getOrElse(throw new RuntimeException(s"Invalid URL or not found in class-loader: $path."))
  def classPathOption(path: String): Option[Content] = {
    val o = Option(Thread.currentThread().getContextClassLoader.getResource(path))
    o.map(classPath)
  }

  def file(file: File): Content
  def file(file: File, contentType: ContentType): Content
  def url(url: URL): Content
  def url(url: URL, contentType: ContentType): Content
  def classPath(url: URL): Content
  def classPath(path: String, contentType: ContentType): Content
}