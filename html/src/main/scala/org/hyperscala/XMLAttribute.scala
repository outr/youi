package org.hyperscala

import org.powerscala.hierarchy.Named

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLAttribute extends Named {
  def name: () => String
  def attributeValue: String
  def attributeValue_=(value: String): Unit
  def shouldRender: Boolean
}
