package org.hyperscala.html

import org.hyperscala.{Unique, Page}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait IdentifyTags {
  this: Page =>

  intercept.init {
    case tag: HTMLTag => if (tag.id() == null) {
      tag.id := Unique()
    }
  }
}
