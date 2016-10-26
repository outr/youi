package io.youi.http

import java.io.File
import java.net.URL

sealed trait Content

class FileContent(file: File) extends Content

class URLContent(url: URL) extends Content

object EmptyContent extends Content

object Content {
  def file(file: File): Content = new FileContent(file)
  def classPath(url: URL): Content = new URLContent(url)
  def classPath(path: String): Content = new URLContent(Thread.currentThread().getContextClassLoader.getResource(path))
  def empty: Content = EmptyContent
}