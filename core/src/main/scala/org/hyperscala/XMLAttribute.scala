package org.hyperscala

import io.HTMLWriter

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait XMLAttribute {
  def name: String
  def write(markup: Markup, writer: HTMLWriter): Unit
  def read(markup: Markup, value: String): Unit
}
