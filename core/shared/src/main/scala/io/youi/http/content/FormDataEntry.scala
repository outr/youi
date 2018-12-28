package io.youi.http.content

import java.io.File

import io.youi.http.Headers

sealed trait FormDataEntry {
  def headers: Headers
}

case class FileEntry(fileName: String, file: File, headers: Headers) extends FormDataEntry

case class StringEntry(value: String, headers: Headers) extends FormDataEntry