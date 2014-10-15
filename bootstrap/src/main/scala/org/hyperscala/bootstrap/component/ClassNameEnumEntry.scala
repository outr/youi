package org.hyperscala.bootstrap.component

import org.powerscala.enum.EnumEntry

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ClassNameEnumEntry extends EnumEntry {
  def className: String
}