package io.youi.http

import java.io.File
import java.net.URL

sealed trait Content

class StringContent(value: String) extends Content

class FileContent(file: File) extends Content

class URLContent(url: URL) extends Content

object Content {
  def string(value: String): Content = new StringContent(value)
  def file(file: File): Content = new FileContent(file)
  def url(url: URL): Content = new URLContent(url)
  def classPath(path: String): Content = new URLContent(Thread.currentThread().getContextClassLoader.getResource(path))
}