package org.hyperscala

import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object css {
  implicit class StyleSheetLengthAttribute(a: StyleSheetAttribute[Length]) {
    def +=(value: Double) = {
      a := (a() + value)
    }
  }
}