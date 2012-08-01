package org.hyperscala

import java.util.UUID

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object Unique {
  def apply(): String = UUID.randomUUID().toString.replace("-", "") match {
    case s if (s.charAt(0).isDigit) => apply()
    case s => s
  }
}
