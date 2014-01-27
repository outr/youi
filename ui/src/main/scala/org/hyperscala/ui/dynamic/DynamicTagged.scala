package org.hyperscala.ui.dynamic

import org.hyperscala.html.HTMLTag
import org.powerscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait DynamicTagged[T <: HTMLTag] extends HTMLTag {
  this: T =>

  def dynamicTag: DynamicTag[T]

  dynamicTag(this)

  def regenIds() = {
    id := Unique()
    byTag[HTMLTag].foreach {
      case t => t.id := Unique()
    }
  }
}