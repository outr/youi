package org.hyperscala

import io.HTMLWriter
import org.powerscala.hierarchy.Named

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLAttribute extends Named {
  def name: () => String
  def write(markup: Markup, writer: HTMLWriter): Unit
  def read(markup: Markup, value: String): Unit
}
