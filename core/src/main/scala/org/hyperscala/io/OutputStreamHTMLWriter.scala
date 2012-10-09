package org.hyperscala.io

import java.io.OutputStream

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait OutputStreamHTMLWriter extends HTMLWriter {
  def outputStream: OutputStream

  def write(s: String) = if (s != null) {
    outputStream.write(s.getBytes)
  }
}
