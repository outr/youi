package org.hyperscala.io

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait StringBuilderHTMLWriter extends HTMLWriter {
  val writer = new StringBuilder

  def write(s: String) = writer.append(s)
}
