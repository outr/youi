package org.hyperscala

import io.HTMLWriter

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLAttribute {
  def name: String
  def write(markup: Markup, writer: HTMLWriter): Unit
  def read(markup: Markup, value: String): Unit
}
