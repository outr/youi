package org.hyperscala

import org.powerscala.hierarchy.Named
import org.jdom2.Element

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLAttribute extends Named {
  def name: () => String
  def write(markup: Markup, element: Element): Unit
  def read(markup: Markup, value: String): Unit
}
